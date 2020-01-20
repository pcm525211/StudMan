package com.example.studman;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;

public class MainActivity extends AppCompatActivity {

    //declarations
    Button btn,btn2,btnInstitutes;
    RequestQueue queue;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btnID);
        btn2 = (Button) findViewById(R.id.button);
        btnInstitutes = (Button) findViewById(R.id.btn_institutes);

        //go to institutes

        btnInstitutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Institutes.class);
                startActivity(i);
            }
        });


        //go to login

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),login.class);
                startActivity(i);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),sampleScrolling.class);
                startActivity(i);

            }


            });
        }
    }
