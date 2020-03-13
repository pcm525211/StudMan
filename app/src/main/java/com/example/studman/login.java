package com.example.studman;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class login extends AppCompatActivity {

    EditText txtEmail,txtPassword;
    Button btnLogin;
    Button btnSignUp;
    ProgressBar loginLoading;
    SQLiteDatabase db;
    String loginurl = "https://www.leancerweb.com/studman/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        loginLoading = (ProgressBar) findViewById(R.id.loginLoading);
        loginLoading.setVisibility(View.GONE);

        db=openOrCreateDatabase("studman", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(email VARCHAR,password VARCHAR);");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  i = new Intent(getApplicationContext(),SignUp.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginLoading.setVisibility(View.VISIBLE);
                Map<String, String> postParam= new HashMap<String, String>();
                postParam.put("email", txtEmail.getText().toString());
                postParam.put("password", txtPassword.getText().toString());
                PostRequest(postParam);

            }
        });
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
                                loginLoading.setVisibility(View.GONE);
                                if(response.getString("status").equals("success") ){

                                    db.execSQL("INSERT INTO student VALUES('"+txtEmail.getText().toString()+"','"+txtPassword.getText().toString()+
                                            "');");

                                    Intent  i = new Intent(getApplicationContext(),StdHome.class);
                                    startActivity(i);

                                }else{
//                                    Toast.makeText(getApplicationContext(), response.getString("msg"), Toast.LENGTH_SHORT).show();
                                    Toast.makeText(login.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
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
