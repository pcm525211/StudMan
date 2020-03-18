package com.example.studman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

public class Materials extends AppCompatActivity {


    RecyclerView rv;
    ProgressBar Loading;
    EditText txtMaterial;
    Boolean isloading=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materials);

        final RecyclerView recyclerView = findViewById(R.id.instituteRview);
        txtMaterial = (EditText) findViewById(R.id.txt_institute_search);
        Loading = (ProgressBar) findViewById(R.id.insLoading);
        Loading.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
