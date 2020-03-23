package com.example.studman;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.RequestQueue;

public class MainActivity extends AppCompatActivity {

    //declarations

    CardView btnIns, btnCourses, btnMaterials,btnExams,btnProfile,btnFees;
    RequestQueue queue;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }


        btnIns = (CardView)  findViewById(R.id.btn_ins);
        btnCourses = (CardView) findViewById(R.id.btn_courses);
        btnMaterials = (CardView) findViewById(R.id.btn_material);
        btnExams = (CardView) findViewById(R.id.btn_exam);
        btnFees = (CardView) findViewById(R.id.btn_fees);
        btnProfile = (CardView) findViewById(R.id.btn_account);

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

        //go to Materials
        btnMaterials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Materials.class));
            }
        });

        //Go to exams
        btnExams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Exams.class));
            }
        });

        btnFees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Fee.class));
            }
        });


        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(),Fee.class));
            }
        });


    }



    }
