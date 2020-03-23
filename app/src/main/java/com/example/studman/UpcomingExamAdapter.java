package com.example.studman;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UpcomingExamAdapter extends RecyclerView.Adapter<UpcomingExamAdapter.UpcomingExamAdapterViewHolder> {

    private UpcomingExam[] upcomingExams;
    private Context context;

    public UpcomingExamAdapter(UpcomingExam[] upcomingExams,Context context)
    {
        this.upcomingExams = upcomingExams;
        this.context = context;
    }

    @NonNull
    @Override
    public UpcomingExamAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.exam_list_item_upcoming,parent,false);
        return new UpcomingExamAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingExamAdapterViewHolder holder, int position) {
        final UpcomingExam upcomingExam = upcomingExams[position];
        holder.txtTitle.setText(upcomingExam.getExTitle());
        holder.txtCourseTitle.setText(upcomingExam.getCoursename());
        holder.txtDate.setText(  upcomingExam.getStartDate() +" - "+ upcomingExam.getEndDate() );



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(context,ExamSchedules.class);
                it.putExtra("UserId",upcomingExam.getExId());
                context.startActivity(it);
//                Toast.makeText(context, subject.getSubName() + " clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return upcomingExams.length;
    }

    public class UpcomingExamAdapterViewHolder extends RecyclerView.ViewHolder
    {

        TextView txtTitle,txtCourseTitle,txtDate;

        public UpcomingExamAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtCourseTitle = itemView.findViewById(R.id.txtCourseTitle);
            txtDate = itemView.findViewById(R.id.txtDate);
        }
    }

}
