package com.example.studman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SplashScreen extends AppCompatActivity {

    SQLiteDatabase db;
    String email,password;
    boolean isStored = false;
    String loginurl = "https://www.leancerweb.com/studman/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        db=openOrCreateDatabase("studman", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(email VARCHAR,password VARCHAR);");


        Cursor c=db.rawQuery("SELECT * FROM student", null);

        if(c.moveToFirst()){
         isStored = true;
         email = c.getString(0);
         password = c.getString(1);

            Map<String, String> postParam= new HashMap<String, String>();
            postParam.put("email",email);
            postParam.put("password", password);
            PostRequest(postParam);
        }



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                    if(isStored) {
                        Intent i = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(i);
                    }else{
                        Intent i = new Intent(SplashScreen.this, login.class);
                        startActivity(i);
                    }
                    finish();

            }
        },4000);
    }

    private void PostRequest(Map<String,String> param) {
        RequestQueue queue;
        queue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                loginurl, new JSONObject(param),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try{

                            if(response.getString("status").equals("success") ){
                               isStored = true;

                            }else{
                             isStored = false;
                                Toast.makeText(SplashScreen.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
                            }


                        }catch (JSONException je){}

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "here "+error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        queue.add(jsonObjReq);


    }
}
