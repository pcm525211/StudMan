package com.example.studman;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;


public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.MAViewHolder>{

    private Material[] materials;
    private Context context;
    private long downloadID;

    public MaterialAdapter(Material[] materials,Context context){
        this.materials = materials;
        this.context = context;
    }

    @NonNull
    @Override
    public MAViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.material_list,parent,false);
        return new MAViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MAViewHolder holder, int position) {
        final Material material = materials[position];

        holder.txttitle.setText(material.getMaterialName());
        holder.txttype.setText(material.getMaterialType());

        holder.btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DownloadManager.Request request = new DownloadManager.Request(Uri.parse("https://www.leancerweb.com/studman/materials/content/"+material.getUrl()))
                        .setTitle(material.getMaterialName())
                        .setDescription("Downloading File...")
                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                        .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,material.getUrl())
                        .setAllowedOverMetered(true)
                        .setAllowedOverRoaming(true);

                    DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                    downloadID = dm.enqueue(request);

            }
        });
    }

    @Override
    public int getItemCount() {
        return materials.length;
    }

    public class MAViewHolder extends RecyclerView.ViewHolder{
        TextView txttitle,txttype;
        Button btnDownload;

        public MAViewHolder(View itemView){
            super(itemView);
            txttitle = (TextView) itemView.findViewById(R.id.textMaterialTitle);
            txttype = (TextView) itemView.findViewById(R.id.textMaterialType);
            btnDownload = (Button) itemView.findViewById(R.id.btn_Materialdownload);
        }
    }
}
