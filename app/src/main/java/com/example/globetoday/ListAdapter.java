package com.example.globetoday;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private static final String TAG = ListAdapter.class.getName();
    Context context;
    ArrayList<News> NewsArrayList;

    public ListAdapter(Context context,ArrayList<News> NewsArrayList){
        this.context = context;
        this.NewsArrayList = NewsArrayList;
    }
    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e(TAG, "onCreateViewHolder: started ",null );
        View view = LayoutInflater.from(context).inflate(R.layout.list_item,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,webView.class);
                intent.putExtra("url",NewsArrayList.get(holder.getAdapterPosition()).getUrl());
                context.startActivity(intent);

            }
        });
        String date =NewsArrayList.get(position).getPublishedAt();

        holder.time.setText(date.substring(0,date.indexOf('T')));
        if(NewsArrayList.get(position).getAuthor()!="null") {
            holder.author.setText(NewsArrayList.get(position).getAuthor());
        }
        holder.heading.setText(NewsArrayList.get(position).getTitle());
        holder.content.setText(NewsArrayList.get(position).getDescription());
        Glide.with(context).load(NewsArrayList.get(position).getUrlToImage()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return NewsArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView heading ,content, author , time;
        CardView cardView;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            heading = itemView.findViewById((R.id.HeadlineView));
            content = itemView.findViewById((R.id.contentView));
            author = itemView.findViewById((R.id.AuthorView));
            time = itemView.findViewById((R.id.dateView));
            cardView = itemView.findViewById((R.id.CardView));
            imageView= itemView.findViewById((R.id.ImageView));
        }
    }
}
