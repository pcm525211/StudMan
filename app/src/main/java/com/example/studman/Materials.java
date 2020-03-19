package com.example.studman;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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

        RequestQueue queue;
        queue = Volley.newRequestQueue(getApplicationContext());
        SharedPreferences myshare = getSharedPreferences("userid",MODE_PRIVATE);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                URL+"?user_id="+myshare.getString("userid",""), null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try{

                            if(response.getString("status").equals("success") ){
                                Loading.setVisibility(View.GONE);

                                GsonBuilder gsonBuilder = new GsonBuilder();
                                Gson gson = gsonBuilder.create();
                                materials=  gson.fromJson(response.getJSONArray("data").toString(),Material[].class);
                                MaterialAdapter MA = new MaterialAdapter(materials,Materials.this);
                                recyclerView.setAdapter(MA);
                                MA.notifyDataSetChanged();

                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(Materials.this);

                                builder.setIcon(R.drawable.icon_cancel);
                                builder.setTitle("Message");
                                builder.setMessage(response.getString("msg"));

                                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(Materials.this,MainActivity.class));
                                    }
                                });

                                AlertDialog dialog = builder.create();

                                dialog.show();
                                                               }


                        }catch (JSONException je){}

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "here "+error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsonObjReq);
    }

}
