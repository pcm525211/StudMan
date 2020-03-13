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

public class Courses extends AppCompatActivity {

    public static final String URL = "https://www.leancerweb.com/studman/course/index.php";
    RecyclerView rv;
    ProgressBar insLoading;
    EditText txtCourse;
    Course[] courses;
    Boolean isloading=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        final RecyclerView recyclerView = findViewById(R.id.courseRview);
        txtCourse = (EditText) findViewById(R.id.txt_course_search);
        insLoading = (ProgressBar) findViewById(R.id.courseLoading);
        insLoading.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        txtCourse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Course[] mutedCourses;

                if(!isloading) {
                    if (s.toString().length() > 0) {
                        mutedCourses = getSearchResult(s.toString());
                    } else {
                        mutedCourses = courses;
                    }

                    courseAdapter CA = new courseAdapter(mutedCourses, Courses.this);
                    recyclerView.setAdapter(CA);
                    CA.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,URL , null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(Courses.this, response.toString(), Toast.LENGTH_LONG).show();
                insLoading.setVisibility(View.GONE);
                isloading = false;
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                courses=  gson.fromJson(response.toString(),Course[].class);
                courseAdapter CA = new courseAdapter(courses,Courses.this);
                recyclerView.setAdapter(CA);
                CA.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Courses.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
    private Course[] getSearchResult(String s){
        ArrayList<Course> temp = new ArrayList<Course>();

        for(Course course : courses){
            if(course.getCoursename().toLowerCase().startsWith(s.toLowerCase())){
                temp.add(course);
            }
        }

        return temp.toArray(new Course[temp.size()]);
    }
}
