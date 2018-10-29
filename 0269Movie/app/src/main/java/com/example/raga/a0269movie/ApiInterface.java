package com.example.raga.a0269movie;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    public  static String DB_API = "c299d4d31cd99cc35bd95c51b566fca9";

    @GET("now_playing?api_key="+DB_API)
    Call<Movie> getPlaying();
}
