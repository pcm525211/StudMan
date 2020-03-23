package com.example.studman;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CompleteExamAdapter extends RecyclerView.Adapter<CompleteExamAdapter.CompleteExamAdapterViewHolder> {

    private CompleteExam[] CompleteExams;
    private Context context;

    public CompleteExamAdapter(CompleteExam[] CompleteExams,Context context)
    {
        this.CompleteExams = CompleteExams;
        this.context = context;
    }

    @NonNull
    @Override
    public CompleteExamAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.exam_list_item_upcoming,parent,false);
        return new CompleteExamAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompleteExamAdapterViewHolder holder, int position) {
        final CompleteExam CompleteExam = CompleteExams[position];
        holder.txtTitle.setText(CompleteExam.getExTitle());
        holder.txtCourseTitle.setText(CompleteExam.getCoursename());
        holder.txtDate.setText(  CompleteExam.getStartDate() +" - "+ CompleteExam.getEndDate() );



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(context,ResultDetail.class);
                it.putExtra("UserId",CompleteExam.getExId());
                context.startActivity(it);
//                Toast.makeText(context, subject.getSubName() + " clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return CompleteExams.length;
    }

    public class CompleteExamAdapterViewHolder extends RecyclerView.ViewHolder
    {

        TextView txtTitle,txtCourseTitle,txtDate;

        public CompleteExamAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtCourseTitle = itemView.findViewById(R.id.txtCourseTitle);
            txtDate = itemView.findViewById(R.id.txtDate);
        }
    }

}
