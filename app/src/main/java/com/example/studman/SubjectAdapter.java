package com.example.studman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectAdapterViewHolder> {

    private Subject[] subjects;
    private Context context;

    public SubjectAdapter(Subject[] subjects,Context context)
    {
        this.subjects = subjects;
        this.context = context;
    }

    @NonNull
    @Override
    public SubjectAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.subject_list_item,parent,false);
        return new SubjectAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectAdapterViewHolder holder, int position) {
        final Subject subject = subjects[position];
        holder.txtTitle.setText(subject.getSubName());
        holder.txtDesc.setText(subject.getSubDesc());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent it = new Intent(context,CourseDetail.class);
//                it.putExtra("UserId",course.getCourseId());
//                context.startActivity(it);
//                Toast.makeText(context, subject.getSubName() + " clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjects.length;
    }

    public class SubjectAdapterViewHolder extends RecyclerView.ViewHolder
    {

        TextView txtTitle,txtDesc;

        public SubjectAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDesc = itemView.findViewById(R.id.txtDesc);
        }
    }

}
