package com.example.studman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class login extends AppCompatActivity {

    EditText txtEmail,txtPassword;
    Button btnLogin;
    ProgressBar loginLoading;
    String loginurl = "https://studmanapi.herokuapp.com/student/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        loginLoading = (ProgressBar) findViewById(R.id.loginLoading);
        loginLoading.setVisibility(View.GONE);

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
                            String token = response.getString("token");
                            Toast.makeText(getApplicationContext(), token, Toast.LENGTH_SHORT).show();
                        }catch (JSONException je){}

                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
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
