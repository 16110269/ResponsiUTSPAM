package com.example.raga.a0269movie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView mView;
    MovieAdapter adapter;
    List<Result> results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mView = (RecyclerView)findViewById(R.id.movieView);
        mView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        movieLoad();
    }
    private void movieLoad(){
        ApiInterface api = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<Movie> call = api.getPlaying();
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movie = response.body();
                adapter = new MovieAdapter(results);
                adapter.setData(movie.getResults());
                mView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }
}
