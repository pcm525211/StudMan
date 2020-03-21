package com.example.studman;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CompleteExams#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompleteExams extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CompleteExams() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CompleteExams.
     */

//    Declarations
    private String URL = "https://www.leancerweb.com/studman/exam/complete.php?user_id=";
    private ProgressBar examLoading;
    private CompleteExam[] completeExams;

    // TODO: Rename and change types and number of parameters
    public static CompleteExams newInstance(String param1, String param2) {
        CompleteExams fragment = new CompleteExams();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_complete_exams, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        examLoading = (ProgressBar) getActivity().findViewById(R.id.completeExamLoading);
        final RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.completeExamRview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        SharedPreferences myshare = getActivity().getSharedPreferences("userid",getContext().MODE_PRIVATE);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                URL+myshare.getString("userid",""), null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try{

                            if(response.getString("status").equals("success") ){
                                examLoading.setVisibility(View.GONE);

                                GsonBuilder gsonBuilder = new GsonBuilder();
                                Gson gson = gsonBuilder.create();
                                completeExams=  gson.fromJson(response.getJSONArray("completed").toString(),CompleteExam[].class);
//                                Toast.makeText(getContext(), ""+response.getJSONArray("completed").toString(), Toast.LENGTH_SHORT).show();
                                CompleteExamAdapter CEA = new CompleteExamAdapter(completeExams,getContext());
                                recyclerView.setAdapter(CEA);
                                CEA.notifyDataSetChanged();

                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                                builder.setIcon(R.drawable.icon_cancel);
                                builder.setTitle("Message");
                                builder.setMessage(response.getString("msg"));

                                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        startActivity(new Intent(getContext(),MainActivity.class));
                                        getActivity().finish();
                                    }
                                });

                                AlertDialog dialog = builder.create();

                                dialog.show();
                            }


                        }catch (JSONException je){}

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "here "+error.toString(), Toast.LENGTH_LONG).show();
            }
        });

//

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjReq);
    }
}
