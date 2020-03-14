package com.example.studman;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Random;

public class instituteAdapter extends RecyclerView.Adapter<instituteAdapter.instituAdapterViewHolder> {

    private Institute[] institutes;
    private Context context;

    public instituteAdapter(Institute[] institutes,Context context)
    {
        this.institutes = institutes;
        this.context = context;
    }

    @NonNull
    @Override
    public instituAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item,parent,false);
        return new instituAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull instituAdapterViewHolder holder, int position) {
        final Institute institute = institutes[position];
        holder.txt.setText(institute.getInstName());

        Glide.with(holder.img.getContext()).load("https://www.leancerweb.com/studman/institute/img/"+institute.getPhoto()).into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(context,InstituteDetail.class);
                it.putExtra("UserId",institute.getUserId());
                context.startActivity(it);
//                Toast.makeText(context, institute.getUserId() + " clicked", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return institutes.length;
    }

    public class instituAdapterViewHolder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView txt;
        public instituAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgIcon);
            txt = itemView.findViewById(R.id.textTitle);
        }
    }

}
