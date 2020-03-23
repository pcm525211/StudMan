package com.example.studman;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

public class ExamSchedules extends AppCompatActivity {


    private RecyclerView rv;
    private ProgressBar examShedulesLoading;
    private EditText txtInstitute;
    private ExamSchedule[] examSchedules;
    private Boolean isloading=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_schedules);

        rv = (RecyclerView) findViewById(R.id.examScheduleRview);
        examShedulesLoading = (ProgressBar) findViewById(R.id.examShedulesLoading);

        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String UserId = bundle.getString("UserId");
        String URL = "https://www.leancerweb.com/studman/exam/examschedule.php?exam_id=" + UserId;
        final RecyclerView recyclerView = findViewById(R.id.examScheduleRview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,URL , null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
//                Toast.makeText(Institutes.this, response.toString(), Toast.LENGTH_LONG).show();
                examShedulesLoading.setVisibility(View.GONE);
                isloading = false;
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                examSchedules=  gson.fromJson(response.toString(),ExamSchedule[].class);
                ExamScheduleAdapter ESA = new ExamScheduleAdapter(examSchedules,ExamSchedules.this);
                recyclerView.setAdapter(ESA);
                ESA.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ExamSchedules.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);



    }
}
