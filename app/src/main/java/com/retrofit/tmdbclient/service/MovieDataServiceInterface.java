package com.retrofit.tmdbclient.service;

import com.retrofit.tmdbclient.model.WholeInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDataServiceInterface {

    @GET("movie/popular")
    Call<WholeInfo> getPopularMovies(@Query("api_key") String apiKey);

}
