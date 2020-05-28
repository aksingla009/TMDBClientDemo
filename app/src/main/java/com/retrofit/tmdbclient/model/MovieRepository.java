package com.retrofit.tmdbclient.model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.retrofit.tmdbclient.R;
import com.retrofit.tmdbclient.service.MovieDataServiceInterface;
import com.retrofit.tmdbclient.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private ArrayList<Result> movies = new ArrayList<>();
    private MutableLiveData<List<Result>> mutableLiveData = new MutableLiveData<>();

    private Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Result>> getMutableLiveDataOfAllMovies() {
        MovieDataServiceInterface movieDataServiceInterface = RetrofitInstance.getRetrofitInstance();
        Call<WholeInfo> call = movieDataServiceInterface.getPopularMovies(application.getApplicationContext().getString(R.string.api_key_value));

        call.enqueue(new Callback<WholeInfo>() {
            @Override
            public void onResponse(Call<WholeInfo> call, Response<WholeInfo> response) {
                WholeInfo infoObj = response.body();
                if (infoObj != null && infoObj.getResults() != null) {
                    movies = (ArrayList<Result>) infoObj.getResults();
                    mutableLiveData.setValue(movies);


                }
            }

            @Override
            public void onFailure(Call<WholeInfo> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }
}
