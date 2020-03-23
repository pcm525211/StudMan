package com.example.studman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ExamScheduleAdapter extends RecyclerView.Adapter<ExamScheduleAdapter.ExamScheduleAdapterViewHolder> {

    private ExamSchedule[] examSchedules;
    private Context context;

    public ExamScheduleAdapter(ExamSchedule[] examSchedules, Context context)
    {
        this.examSchedules = examSchedules;
        this.context = context;
    }

    @NonNull
    @Override
    public ExamScheduleAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.exam_schedule_list_item,parent,false);
        return new ExamScheduleAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamScheduleAdapterViewHolder holder, int position) {
        final ExamSchedule examSchedule = examSchedules[position];
        holder.txtTitle.setText(examSchedule.getSubName());
        holder.txtDesc.setText(examSchedule.getSyllabus());
        holder.txtDate.setText(examSchedule.getDate());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent it = new Intent(context,InstituteDetail.class);
//                it.putExtra("UserId",institute.getUserId());
//                context.startActivity(it);
//                Toast.makeText(context, institute.getUserId() + " clicked", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return examSchedules.length;
    }

    public class ExamScheduleAdapterViewHolder extends RecyclerView.ViewHolder
    {

        TextView txtTitle,txtDate,txtDesc;
        public ExamScheduleAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtDesc = itemView.findViewById(R.id.txtDesc);
        }
    }

}
