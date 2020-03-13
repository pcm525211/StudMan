package com.example.studman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class courseAdapter extends RecyclerView.Adapter<courseAdapter.courseAdapterViewHolder> {

    private Course[] courses;
    private Context context;

    public courseAdapter(Course[] courses,Context context)
    {
        this.courses = courses;
        this.context = context;
    }

    @NonNull
    @Override
    public courseAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item,parent,false);
        return new courseAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull courseAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class courseAdapterViewHolder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView txt;
        public courseAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgIcon);
            txt = itemView.findViewById(R.id.textTitle);
        }
    }

}
