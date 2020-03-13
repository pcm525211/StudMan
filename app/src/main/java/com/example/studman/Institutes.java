package com.example.studman;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import java.util.ArrayList;

public class Institutes extends AppCompatActivity {

    public static final String URL = "https://www.leancerweb.com/studman/institute/index.php";
    RecyclerView rv;
    ProgressBar insLoading;
    EditText txtInstitute;
    Institute[] institutes;
    Boolean isloading=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institutes);

        final RecyclerView recyclerView = findViewById(R.id.instituteRview);
        txtInstitute = (EditText) findViewById(R.id.txt_institute_search);
        insLoading = (ProgressBar) findViewById(R.id.insLoading);
        insLoading.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
            txtInstitute.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Institute[] mutedinstitutes;

                    if(!isloading) {
                        if (s.toString().length() > 0) {
                            mutedinstitutes = getSearchResult(s.toString());
                        } else {
                            mutedinstitutes = institutes;
                        }

                        instituteAdapter IA = new instituteAdapter(mutedinstitutes, Institutes.this);
                        recyclerView.setAdapter(IA);
                        IA.notifyDataSetChanged();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });



        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,URL , null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
//                Toast.makeText(Intitute.this, response.toString(), Toast.LENGTH_SHORT).show();
                insLoading.setVisibility(View.GONE);
                isloading = false;
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                institutes=  gson.fromJson(response.toString(),Institute[].class);
                instituteAdapter IA = new instituteAdapter(institutes,Institutes.this);
                recyclerView.setAdapter(IA);
                IA.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Institutes.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    private Institute[] getSearchResult(String s){
        ArrayList<Institute> temp = new ArrayList<Institute>();

        for(Institute institute : institutes){
            if(institute.getInstName().toLowerCase().startsWith(s.toLowerCase())){
                temp.add(institute);
            }
        }

        return temp.toArray(new Institute[temp.size()]);
    }
}
