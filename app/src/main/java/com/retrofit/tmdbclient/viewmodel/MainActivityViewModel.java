package com.retrofit.tmdbclient.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.retrofit.tmdbclient.model.MovieRepository;
import com.retrofit.tmdbclient.model.Result;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
    }

    public LiveData<List<Result>> getAllMovies(){

        return movieRepository.getMutableLiveDataOfAllMovies();
    }
}
