package com.arifcebe.favoritmovie.rest;

import com.arifcebe.favoritmovie.model.Genre;
import com.arifcebe.favoritmovie.model.GenreResponse;
import com.arifcebe.favoritmovie.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by arifcebe
 * on Jan 1/10/17 11:09.
 * Project : FavoritMovie
 * <p>
 * Lebih Baik Pulang Nama, Daripada Gagal di Medan Laga
 */

public interface ApiInterface {

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovie(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MovieResponse> getMovieDetail(@Path("id") int id,@Query("api_key") String apiKey);

    @GET("genre/movie/list")
    Call<GenreResponse> getGenre(@Query("api_key") String apiKey,@Query("language") String lang);

}