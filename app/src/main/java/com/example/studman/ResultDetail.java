package com.example.studman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class ResultDetail extends AppCompatActivity {

    private ProgressBar resultLoading;
    private Result result;
    private ResultSubject[] resultSubjects;
    private TextView txtExamName, txtCourseName, txtDate, txtPercentage, txtClass, txtTotalMarks;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_detail);

//   Declaration
        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String UserId = bundle.getString("UserId");
        resultLoading = (ProgressBar) findViewById(R.id.resultLoading);
        final String URL = "https://www.leancerweb.com/studman/result/detailresult.php?exam_id="+UserId+"&user_id=";

        txtExamName = (TextView) findViewById(R.id.txtExamTitle);
        txtCourseName = (TextView) findViewById(R.id.txtCourseTitle);
        txtDate = (TextView) findViewById   (R.id.txtDate);
        txtPercentage = (TextView) findViewById(R.id.txtPercentage);
        txtClass = (TextView) findViewById(R.id.txtClass);
        txtTotalMarks = (TextView) findViewById(R.id.txtTotalMarks);
        recyclerView = (RecyclerView) findViewById(R.id.examResultSubjectRview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SharedPreferences myshare = getSharedPreferences("userid",MODE_PRIVATE);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL+myshare.getString("userid",""), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                result =  gson.fromJson(response.toString(),Result.class);

                txtExamName.setText(result.getExTitle());
                txtCourseName.setText(result.getCoursename());
                txtDate.setText(result.getResDate());

                float per = ( Float.parseFloat(result.getMarksObtain()) * 100 ) / Float.parseFloat(result.getTotalMarks());

                txtPercentage.setText(new DecimalFormat("##.#").format(per) + "%");
                txtClass.setText(result.getClass_());
                txtTotalMarks.setText(result.getMarksObtain() + "/" + result.getTotalMarks());

                try {
                    resultSubjects=  gson.fromJson(response.getJSONArray("subjects").toString(),ResultSubject[].class);
                    ResultSubjectAdapter RSA = new ResultSubjectAdapter(resultSubjects, ResultDetail.this);
                    recyclerView.setAdapter(RSA);
                    RSA.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                Toast.makeText(ResultDetail.this, result.getResultSubjects().toString(), Toast.LENGTH_LONG).show();
                resultLoading.setVisibility(View.GONE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ResultDetail.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue1 = Volley.newRequestQueue(this);
        requestQueue1.add(jsonObjectRequest);
    }
}
