package com.arifcebe.favoritmovie.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by arifcebe
 * on Jan 1/10/17 11:04.
 * Project : FavoritMovie
 * <p>
 * Lebih Baik Pulang Nama, Daripada Gagal di Medan Laga
 */

public class ApiClient {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String API_KEY = "636e6f0f33e3718b37e0a13df642bdfd";
    public static final String IMAGE_PATH = "https://image.tmdb.org/t/p/w500/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
