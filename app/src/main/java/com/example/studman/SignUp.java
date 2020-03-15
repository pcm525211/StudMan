package com.example.studman;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
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

public class SignUp extends AppCompatActivity {

    EditText txtEmail,txtPassword,txtName;
    String gender;
    Button btnRegister,btnSignin;
    ProgressBar registerLoading;
    SQLiteDatabase db;
    Boolean isValidate = true;
    String RegisterUrl = "https://www.leancerweb.com/studman/student/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        txtEmail = (EditText) findViewById(R.id.txtRegisterEmail);
        txtPassword = (EditText) findViewById(R.id.txtRegisterPassword);
        txtName = (EditText) findViewById(R.id.txtRegisterName);

        db=openOrCreateDatabase("studman", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(email VARCHAR,password VARCHAR);");

        registerLoading = (ProgressBar) findViewById(R.id.registerLoading);
        registerLoading.setVisibility(View.GONE);
    }

    public void onRegisterButtonClick(View view){

        //InputValidation
        this.validateInput();

        if(isValidate) {
            registerLoading.setVisibility(View.VISIBLE);
            Map<String, String> postParam = new HashMap<String, String>();
            postParam.put("email", txtEmail.getText().toString());
            postParam.put("password", txtPassword.getText().toString());
            postParam.put("name", txtName.getText().toString());
            postParam.put("gender", gender);
            PostRequest(postParam);
        }

    }

    public void onSigninButtonClick(View view){
        Intent i = new Intent(getApplicationContext(),login.class);
        startActivity(i);
    }

    public void onGenderRadioClick(View view){

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radioMale:
                if (checked)
                    this.gender = "m";
                    break;
            case R.id.radioFemale:
                if (checked)
                    this.gender = "f";
                    break;
        }

    }

    private void validateInput(){
        if(txtName.getText().toString().length() == 0){
            txtName.setError("Name Is Required");
            isValidate = false;
        }
        if(txtEmail.getText().toString().length() == 0){
            txtEmail.setError("Email is Required");
            isValidate = false;
        }
        if(txtEmail.getText().toString().length() > 0 && !txtEmail.getText().toString().matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")){
            txtEmail.setError("Please Enter Valid Email");
            isValidate = false;
        }
        if(txtPassword.getText().toString().length() == 0){
            txtPassword.setError("Password is Required");
            isValidate = false;
        }
        if(gender == null){
            Toast.makeText(SignUp.this,"Please Select Gender", Toast.LENGTH_SHORT).show();
            isValidate = false;
        }
    }

    private void PostRequest(Map<String,String> param) {
        RequestQueue queue;
        queue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                RegisterUrl, new JSONObject(param),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            registerLoading.setVisibility(View.GONE);
                            if(response.getString("status").equals("success") ){

                                db.execSQL("INSERT INTO student VALUES('"+txtEmail.getText().toString()+"','"+txtPassword.getText().toString()+
                                        "');");

                                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(i);
                            }else{
                                Toast.makeText(SignUp.this,response.getString("msg"), Toast.LENGTH_SHORT).show();
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
