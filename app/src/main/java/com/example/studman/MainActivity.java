package com.example.studman;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.RequestQueue;

public class MainActivity extends AppCompatActivity {

    //declarations

    CardView btnIns;
    CardView btnCourses;
    RequestQueue queue;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btnIns = (CardView)  findViewById(R.id.btn_ins);
        btnCourses = (CardView) findViewById(R.id.btn_courses);

        //go to institutes

        btnIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
