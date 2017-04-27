package com.arifcebe.favoritmovie.module.main.presenter;

import android.content.Context;

import com.arifcebe.favoritmovie.base.AppLog;
import com.arifcebe.favoritmovie.model.Genre;
import com.arifcebe.favoritmovie.model.GenreResponse;
import com.arifcebe.favoritmovie.model.Movie;
import com.arifcebe.favoritmovie.model.MovieResponse;
import com.arifcebe.favoritmovie.module.main.MainInterface;
import com.arifcebe.favoritmovie.rest.ApiClient;
import com.arifcebe.favoritmovie.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by arifcebe
 * on Jan 1/10/17 16:12.
 * Project : FavoritMovie
 * <p>
 * Lebih Baik Pulang Nama, Daripada Gagal di Medan Laga
 */

public class MainPresenter {

    private static final String TAG = MainPresenter.class.getSimpleName();
    private Context context;
    private MainInterface mainInterfaceView;
    private ApiInterface apiService;
    private int countService = 0;

    public MainPresenter(Context context, MainInterface mainInterfaceView) {
        this.context = context;
        this.mainInterfaceView = mainInterfaceView;

        apiService = ApiClient.getClient().create(ApiInterface.class);
    }

    public void fetchFavoriteMovie() {
        mainInterfaceView.onLoading(true);
        Call<MovieResponse> call = apiService.getTopRatedMovie(ApiClient.API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();
                stopLoader();
                mainInterfaceView.successLoadFavoriteMovie(movies);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                stopLoader();
            }
        });
    }

    public void fetchGenre() {

        Call<GenreResponse> call = apiService.getGenre(ApiClient.API_KEY,"en-US");
        call.enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                stopLoader();
                List<Genre> genreList = response.body().getGenres();
                mainInterfaceView.successLoadGenre(genreList);
            }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {
                AppLog.getInstance().showLog(TAG,"failer to get genre "+t.toString());
                stopLoader();
            }
        });
    }

    private void stopLoader(){
        countService = countService + 1;
        if(countService == 2){
            mainInterfaceView.onLoading(false);
        }
        AppLog.getInstance().showLog(TAG,"count service "+countService);
    }
}
