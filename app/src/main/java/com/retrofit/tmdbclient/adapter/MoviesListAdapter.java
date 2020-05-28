package com.retrofit.tmdbclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.retrofit.tmdbclient.R;
import com.retrofit.tmdbclient.databinding.MovieListItemBinding;
import com.retrofit.tmdbclient.model.Result;
import com.retrofit.tmdbclient.view.MovieActivity;

import java.util.ArrayList;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MoviesViewHolder>{

    private Context context;
    private ArrayList<Result> moviesList;

    public MoviesListAdapter(Context context, ArrayList<Result> moviesList) {
        this.context = context;
        this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        MovieListItemBinding movieListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.movie_list_item,parent,false);

        return new MoviesViewHolder(movieListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {

        Result movie = moviesList.get(position);

        holder.movieListItemBinding.setMovieentityanyname(movie);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder{

        private MovieListItemBinding movieListItemBinding;

        public MoviesViewHolder(@NonNull MovieListItemBinding movieListItemBinding) {

            super(movieListItemBinding.getRoot());

            this.movieListItemBinding = movieListItemBinding;

            movieListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if(position != RecyclerView.NO_POSITION){
                        Result selectedMovie = moviesList.get(position);

                        Intent intent = new Intent(context, MovieActivity.class);
                        intent.putExtra("SELECTED_MOVIE",selectedMovie);
                        context.startActivity(intent);
                    }

                }
            });
        }
    }
}
