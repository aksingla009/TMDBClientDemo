package com.retrofit.tmdbclient.view;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.retrofit.tmdbclient.R;
import com.retrofit.tmdbclient.adapter.MoviesListAdapter;
import com.retrofit.tmdbclient.databinding.ActivityMainBinding;
import com.retrofit.tmdbclient.model.Result;
import com.retrofit.tmdbclient.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Result> movies;
    private RecyclerView moviesRecyclerView;
    private MoviesListAdapter adapter;

    private SwipeRefreshLayout swipeRefreshLayout;

    private MainActivityViewModel mainActivityViewModel;

    private ActivityMainBinding activityMainBindingInstance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("TMDB POPULAR MOVIES TODAY");

        activityMainBindingInstance = DataBindingUtil.setContentView(this,R.layout.activity_main);

        //for android view model
        mainActivityViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(MainActivityViewModel.class);

        //for normal view model use like this
        //mainActivityViewModel = new ViewModelProvider(MainActivity.this).get(MainActivityViewModel.class);

        getPopularMovies();

//        swipeRefreshLayout = findViewById(R.id.swipeLayout);
        swipeRefreshLayout = activityMainBindingInstance.swipeLayout;

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();

            }
        });

    }

    private void getPopularMovies() {

        mainActivityViewModel.getAllMovies().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> resultsFromLivedata) {
                movies = (ArrayList<Result>) resultsFromLivedata;
                showOnRecyclerView();
            }
        });

    }

    private void showOnRecyclerView() {
//        moviesRecyclerView = findViewById(R.id.moviesRV);
        moviesRecyclerView = activityMainBindingInstance.moviesRV;

        adapter = new MoviesListAdapter(MainActivity.this, movies);
        int spanCount = 2;
        // 2 for portrait and 4 for landscape
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            spanCount = 2;
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            spanCount = 4;
        }

        moviesRecyclerView.setLayoutManager(new GridLayoutManager(this, spanCount));
        moviesRecyclerView.setItemAnimator(new DefaultItemAnimator());

        moviesRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //swipeRefreshLayout.setRefreshing(false);

    }
}
