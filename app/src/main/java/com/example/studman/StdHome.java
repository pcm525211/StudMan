package com.example.studman;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StdHome extends AppCompatActivity {
    JSONObject res = null;
    String url = "http://adamlye.freeasphost.net/_____2_/adamlye/student";
    ListView studList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_std_home);

        studList = (ListView) findViewById(R.id.studentList);

        getRequest();



    }

    private void getRequest(){

        RequestQueue queue;

        queue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray array = response.getJSONArray("data");
                    String[] nameData = new String[array.length()];

                    for (int i = 0 ; i< array.length();i++){
                        JSONObject j = array.getJSONObject(i);
                        nameData[i] = j.getString("email");
                    }


                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,android.R.id.text1,nameData);

                    studList.setAdapter(adapter);

                }catch (JSONException je){}
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "here "+error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        queue.add(req);
    }
}
