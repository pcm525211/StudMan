package com.example.studman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;

public class ResultSubjectAdapter extends RecyclerView.Adapter<ResultSubjectAdapter.ResultSubjectAdapterViewHolder> {

    private ResultSubject[] resultSubjects;
    private Context context;

    public ResultSubjectAdapter(ResultSubject[] resultSubjects,Context context)
    {
        this.resultSubjects = resultSubjects;
        this.context = context;
    }

    @NonNull
    @Override
    public ResultSubjectAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.result_subject_list_item,parent,false);
        return new ResultSubjectAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultSubjectAdapterViewHolder holder, int position) {
        final ResultSubject resultSubject = resultSubjects[position];
        holder.txtSubjectTitle.setText(resultSubject.getSubName());

        float per = ( Float.parseFloat(resultSubject.getMarksObtain()) * 100 ) / Float.parseFloat(resultSubject.getTotalMarks());
        holder.txtSubjectPercentage.setText(new DecimalFormat("##.#").format(per) + "%");
        holder.txtSubjectClass.setText( "Grade "+ resultSubject.getSubjectClass(per));
        holder.txtSubjectMarks.setText( resultSubject.getMarksObtain() + "/" + resultSubject.getTotalMarks());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent it = new Intent(context,ExamSchedules.class);
//                it.putExtra("UserId",resultSubject.getExId());
//                context.startActivity(it);
//                Toast.makeText(context, subject.getSubName() + " clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultSubjects.length;
    }

    public class ResultSubjectAdapterViewHolder extends RecyclerView.ViewHolder
    {

        TextView txtSubjectTitle,txtSubjectPercentage,txtSubjectClass, txtSubjectMarks;

        public ResultSubjectAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSubjectTitle = (TextView) itemView.findViewById(R.id.txtSubjectTitle);
            txtSubjectPercentage = (TextView) itemView.findViewById(R.id.txtSubjectPercentage);
            txtSubjectClass = (TextView) itemView.findViewById(R.id.txtSubjectClass);
            txtSubjectMarks = (TextView) itemView.findViewById(R.id.txtSubjectMarks);

        }
    }

}
