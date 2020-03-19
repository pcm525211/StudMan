package com.example.studman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

public class Materials extends AppCompatActivity {


    RecyclerView rv;
    ProgressBar Loading;
    EditText txtMaterial;
    Material[] materials;
    Boolean isloading=true;
    String URL = "https://www.leancerweb.com/studman/materials/index.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materials);

        final RecyclerView recyclerView = findViewById(R.id.materialRview);
        txtMaterial = (EditText) findViewById(R.id.txt_material_search);
        Loading = (ProgressBar) findViewById(R.id.materialLoading);
        Loading.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,URL , null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Loading.setVisibility(View.GONE);

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                materials=  gson.fromJson(response.toString(),Material[].class);
                MaterialAdapter MA = new MaterialAdapter(materials,Materials.this);
                recyclerView.setAdapter(MA);
                MA.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Materials.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

}
