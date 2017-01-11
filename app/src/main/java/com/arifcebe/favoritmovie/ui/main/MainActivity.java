package com.arifcebe.favoritmovie.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.arifcebe.favoritmovie.R;
import com.arifcebe.favoritmovie.model.Genre;
import com.arifcebe.favoritmovie.model.GenreResponse;
import com.arifcebe.favoritmovie.model.Movie;
import com.arifcebe.favoritmovie.model.MovieResponse;
import com.arifcebe.favoritmovie.rest.ApiClient;
import com.arifcebe.favoritmovie.rest.ApiInterface;
import com.arifcebe.favoritmovie.ui.MovieAdapter;
import com.arifcebe.favoritmovie.ui.main.adapter.GenreAdapter;
import com.arifcebe.favoritmovie.ui.main.adapter.NewFilmAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.list_new_movie)
    RecyclerView recyclerView;
    @BindView(R.id.list_genre)
    RecyclerView recyclerViewGenre;

    private NewFilmAdapter adapter;
    private GenreAdapter genreAdapter;
    private ApiInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewGenre.setHasFixedSize(true);
        recyclerViewGenre.setLayoutManager(new LinearLayoutManager(this));

        apiService = ApiClient.getClient().create(ApiInterface.class);

        fetchFavoriteMovie();
        fetchGenre();

    }

    public void fetchFavoriteMovie() {
        Call<MovieResponse> call = apiService.getTopRatedMovie(ApiClient.API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();
                Log.d(TAG, "size movies " + movies.size());
                adapter = new NewFilmAdapter(MainActivity.this, movies);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d(TAG, "failure connect " + t.toString());
            }
        });
    }

    public void fetchGenre() {
        Call<GenreResponse> call = apiService.getGenre(ApiClient.API_KEY,"en-US");
        call.enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                Log.d(TAG,"response genre "+response.body().toString());
                List<Genre> genreList = response.body().getGenres();
                genreAdapter = new GenreAdapter(MainActivity.this,genreList);
                recyclerViewGenre.setAdapter(genreAdapter);
                genreAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {
                Log.d(TAG,"failer to get genre "+t.toString());
            }
        });
    }
}
