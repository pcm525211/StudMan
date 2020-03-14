package com.example.studman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

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
        final Course course = courses[position];
        holder.txt.setText(course.getCoursename());
        Glide.with(holder.img.getContext()).load("https://www.leancerweb.com/studman/course/img/"+course.getThumbnailurl()).into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent it = new Intent(context,Institute.class);
//                it.putExtra("uname-",institute.getInsName());
//                context.startActivity(it);
                Toast.makeText(context, course.getCoursename() + " clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return courses.length;
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
