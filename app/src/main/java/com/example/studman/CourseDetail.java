package com.example.studman;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class CourseDetail extends AppCompatActivity {

    ImageView CourseImage;
    ProgressBar LoadLoading;
    Course course;
    Toolbar toolbar;
    TextView CourseDescription;
    TextView CoursePrice;
    LinearLayout LinPre;
    LinearLayout LinWL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        CourseDescription = (TextView) findViewById(R.id.txt_description);
        CoursePrice =  (TextView) findViewById(R.id.txt_price);
        LinPre = (LinearLayout) findViewById(R.id.lin_prerequirement);
        LinWL = (LinearLayout) findViewById(R.id.lin_whatlearn);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String UserId = bundle.getString("UserId");
        final String URL = "https://www.leancerweb.com/studman/course/index.php?course_id="+UserId;
//        final String URL_COURSES = "https://www.leancerweb.com/studman/course/index.php?ins_id="+UserId;


        CourseImage = (ImageView)findViewById(R.id.CourseImage);

//        InsAbout = (TextView)findViewById(R.id.txt_about);
        LoadLoading = (ProgressBar)findViewById(R.id.LoadLoading);
        LoadLoading.setVisibility(ProgressBar.VISIBLE);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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
                course=  gson.fromJson(response.toString(),Course.class);
                CourseDescription.setText(course.getDescription());
                CoursePrice.setText("₹ "+course.getFee()+"/-");
                toolbar.setTitle(course.getCoursename());
                Glide.with(CourseImage.getContext()).load("https://www.leancerweb.com/studman/course/img/"+course.getThumbnailurl()).into(CourseImage);


//          For pre requirements

                String pre[] = course.getPrerequirement().split(",,");
                int i;
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(10,10,10,10);

                for( i=0;i< pre.length;i++)
                {
                    LinearLayout linearLayout = new LinearLayout(getApplicationContext());
                    linearLayout.setLayoutParams(params);

                    TextView myPre = new TextView(getApplicationContext());
                    myPre.setText((i+1)+ " ) "+pre[i]);
                    myPre.setPadding(5,5,5,5);
                    myPre.setTextColor(getResources().getColor(R.color.white));
                    myPre.setTextSize(20);
                    myPre.setBackgroundColor(getResources().getColor(R.color.colorAccent)  );
                    linearLayout.addView(myPre);
                    LinPre.addView(linearLayout);

                }
//          For What you will learn
                String wl[] = course.getWhatlearn().split(",,");
                for( i=0;i< wl.length;i++)
                {
                    LinearLayout linearLayout = new LinearLayout(getApplicationContext());
                    linearLayout.setLayoutParams(params);
                    TextView myPre = new TextView(getApplicationContext());
                    myPre.setText((i+1)+ " ) "+wl[i]);
                    myPre.setPadding(5,5,5,5);
                    myPre.setTextColor(getResources().getColor(R.color.white));
                    myPre.setTextSize(20);
                    myPre.setBackgroundColor(getResources().getColor(R.color.colorYellow)  );
                    linearLayout.addView(myPre);
                    LinWL.addView(linearLayout);

                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CourseDetail.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue1 = Volley.newRequestQueue(this);
        requestQueue1.add(jsonObjectRequest);
    }
}
