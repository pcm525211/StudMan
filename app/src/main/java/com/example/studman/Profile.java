package com.example.studman;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {

    private ProgressDialog progressDialog;
    CircleImageView usericon,courseicon;
    TextView username,coursename,enrolldate,email;
    String userid;
    String URL = "https://www.leancerweb.com/studman/profile/index.php?";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        usericon = (CircleImageView) findViewById(R.id.usericon);
        courseicon = (CircleImageView) findViewById(R.id.universityicon);

        username = (TextView) findViewById(R.id.username);
        coursename = (TextView) findViewById(R.id.profilecoursename);
        enrolldate = (TextView) findViewById(R.id.enrolldate);
        email = (TextView) findViewById(R.id.profileemail);



        final SharedPreferences myshare = getSharedPreferences("userid",MODE_PRIVATE);
        userid = myshare.getString("userid","");

        progressDialog = new ProgressDialog(Profile.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        getProfile(userid);


    }

    private void getProfile(String userId){
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                URL+"user_id="+userId, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            progressDialog.dismiss();
                            Glide.with(usericon.getContext()).load("https://www.leancerweb.com/studman/profile/upload/"+response.getString("photo")).into(usericon);
                            username.setText(response.getString("name"));
                            email.setText(response.getString("email"));
                            JSONObject enroll = response.getJSONObject("enroll");
                            if(enroll != null){
                                Glide.with(courseicon.getContext()).load("https://www.leancerweb.com/studman/course/img/"+enroll.getString("thumbnailurl")).into(courseicon);
                                coursename.setText(enroll.getString("coursename"));
                                enrolldate.setText("Enroll Since : "+enroll.getString("enrolldate"));
                            }else{
                                coursename.setText("No Course Enrolled Yet");
                                enrolldate.setText("");
                            }
                        }catch (JSONException je){}

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Profile.this, "here "+error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);
    }
}
