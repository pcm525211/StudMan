package com.example.studman;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {

    private ProgressDialog progressDialog;
    CircleImageView usericon,courseicon;
    TextView username,coursename,enrolldate,email,imageName,uploadingText;
    private int PICK_PROFILE_IMAGE_REQUEST = 1;
    SQLiteDatabase db;
    String userid,encodedImage;
    Bitmap bitmap = null;
    AlertDialog dialog;
    boolean isUploaded = false;
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

    public void onProfilePicEdit(View view){
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.upload_image_dialog, null);
        imageName = alertLayout.findViewById(R.id.txt_slectedImage);
        uploadingText = alertLayout.findViewById(R.id.txt_UploadingImage);
        final Button selectButton = alertLayout.findViewById(R.id.btn_selectimage);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Update Image");
        alert.setView(alertLayout);
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
               dialog.dismiss();
            }
        });
        alert.setPositiveButton("Update", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!isUploaded){
                    if(bitmap != null){
                        uploadingText.setText("Uploading....");
                        uploadToserver();
                    }else{
                        uploadingText.setText("Please Select Image");
                        uploadingText.setTextColor(Color.RED);
                    }
                }
            }
        });

        dialog = alert.create();
        dialog.show();

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

    }

    private void selectImage(){
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_PROFILE_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode,data);

        if(requestCode == PICK_PROFILE_IMAGE_REQUEST && resultCode ==
                RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            String fileName = getFileName(filePath);
            imageName.setText(fileName + " Selected");

            try{
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream .toByteArray();
                encodedImage = Base64.encodeToString(byteArray,Base64.DEFAULT);
            }catch (Exception e){}
        }
    }
    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    private void uploadToserver(){
        Map<String, String> postParam = new HashMap<String, String>();
        postParam.put("image", encodedImage);


        RequestQueue queue;
        queue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                "", new JSONObject(postParam),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try{

                            if(response.getString("status").equals("success") ){
                                uploadingText.setText("Uploaded Success");
                                isUploaded = true;
                                usericon.setImageBitmap(bitmap);

                            }else{
                                uploadingText.setText("Error in Uploading Image");
                                uploadingText.setTextColor(Color.RED);
                                Toast.makeText(Profile.this,response.getString("msg"), Toast.LENGTH_SHORT).show();
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

    public void onLogoutButton(View v){
        db=openOrCreateDatabase("studman", Context.MODE_PRIVATE, null);
        db.execSQL("DROP TABLE student;");
        Intent i = new Intent(this, login.class);        // Specify any activity here e.g. home or splash or login etc
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("EXIT", true);
        startActivity(i);
        this.finish();
    }
}
