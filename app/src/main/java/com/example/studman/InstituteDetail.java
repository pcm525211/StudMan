package com.example.studman;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

public class InstituteDetail extends AppCompatActivity {

    Institute institute;
    TextView InsAbout;
    ImageView InsImage;
    ProgressBar LoadLoading;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute_detail);

//   Declaration
        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String UserId = bundle.getString("UserId");
        final String URL = "https://www.leancerweb.com/studman/institute/index.php?ins_id="+UserId;
        toolbar= (Toolbar) findViewById(R.id.Toolbar);
        InsImage = (ImageView)findViewById(R.id.InsImage);
        InsAbout = (TextView)findViewById(R.id.txt_about);
        LoadLoading = (ProgressBar)findViewById(R.id.LoadLoading);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        LoadLoading.setVisibility(ProgressBar.VISIBLE);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                LoadLoading.setVisibility(View.GONE);
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                institute=  gson.fromJson(response.toString(),Institute.class);

                InsAbout.setText(institute.getAbout());
                toolbar.setTitle(institute.getInstName());
                Glide.with(InsImage.getContext()).load("https://www.leancerweb.com/studman/institute/img/"+institute.getPhoto()).into(InsImage);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(InstituteDetail.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
}
