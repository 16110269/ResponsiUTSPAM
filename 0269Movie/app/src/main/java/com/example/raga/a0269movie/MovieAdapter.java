package com.example.raga.a0269movie;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    List<Result> resutls;

    public MovieAdapter(List<Result> resutls) {
        this.resutls = resutls;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_movie, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieHolder holder, final int position) {
        holder.Title.setText(resutls.get(position).getTitle());
        holder.Overview.setText(resutls.get(position).getOverview());
        Picasso.with(holder.itemView.getContext())
                .load("http://image.tmdb.org/t/p/w185/"+resutls.get(position).getPosterPath())
                .into(holder.Poster);

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Result data = resutls.get(position);
                Intent i = new Intent(holder.itemView.getContext(),DetailMovei.class);
                i.putExtra("movie", new GsonBuilder().create().toJson(data));
                holder.itemView.getContext().startActivity(i);
            }
        });
    }


    public void setData(List<Result>results){
        this.resutls = results;
    }

    @Override
    public int getItemCount() {
        return resutls.size();
    }
    class MovieHolder extends RecyclerView.ViewHolder{
        ImageView Poster;
        TextView Title, Overview;
        public MovieHolder(View itemView) {
            super(itemView);
            Poster = (ImageView)itemView.findViewById(R.id.poster);
            Title = (TextView)itemView.findViewById(R.id.title);
            Overview = (TextView)itemView.findViewById(R.id.overview);


        }
    }
}
