package com.example.studman;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;

public class MainActivity extends AppCompatActivity {

    //declarations
    Button btn,btn2,btnInstitutes,btnCourses;
    RequestQueue queue;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnInstitutes = (Button) findViewById(R.id.btn_institutes);
        btnCourses = (Button) findViewById(R.id.btn_courses);

        //go to institutes

        btnInstitutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Institutes.class);
                startActivity(i);
            }
        });

        //go to courses

        btnCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Courses.class);
                startActivity(i);
            }
        });


        }
    }
