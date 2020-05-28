package com.retrofit.tmdbclient.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.retrofit.tmdbclient.R;
import com.retrofit.tmdbclient.databinding.ActivityMovieBinding;
import com.retrofit.tmdbclient.model.Result;

public class MovieActivity extends AppCompatActivity {

    private Result selectedMovie;
  /*  private ImageView movieImageViewLarge;
    private String imagePath;

    private TextView movieTitle, movieSynopsis, movieRating, movieReleaseDate;*/

    private ActivityMovieBinding activityMovieBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        activityMovieBinding = DataBindingUtil.setContentView(this,R.layout.activity_movie);

       /* movieImageViewLarge = findViewById(R.id.ivMovieLarge);

        movieTitle = (TextView) findViewById(R.id.tvMovieTitle);
        movieSynopsis = (TextView) findViewById(R.id.tvPlotsynopsis);
        movieRating = (TextView) findViewById(R.id.tvMovieRating);
        movieReleaseDate = (TextView) findViewById(R.id.tvReleaseDate);*/

        Intent intent = getIntent();
        if(intent.hasExtra("SELECTED_MOVIE")){
            selectedMovie = intent.getParcelableExtra("SELECTED_MOVIE");

            activityMovieBinding.setSelectedMovie(selectedMovie);
/*            imagePath = "https://image.tmdb.org/t/p/w500/"+selectedMovie.getPosterPath();
            Glide.with(this).load(imagePath).thumbnail(0.1f).into(movieImageViewLarge);
            getSupportActionBar().setTitle(selectedMovie.getOriginalTitle());

            movieTitle.setText(selectedMovie.getTitle());
            movieSynopsis.setText(selectedMovie.getOverview());
            movieRating.setText(Double.toString(selectedMovie.getVoteAverage()));
            movieReleaseDate.setText(selectedMovie.getReleaseDate());*/
        }

    }

}
