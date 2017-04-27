package com.arifcebe.favoritmovie.module.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arifcebe.favoritmovie.R;
import com.arifcebe.favoritmovie.base.AppProgressDialog;
import com.arifcebe.favoritmovie.model.Genre;
import com.arifcebe.favoritmovie.model.Movie;
import com.arifcebe.favoritmovie.module.main.adapter.GenreAdapter;
import com.arifcebe.favoritmovie.module.main.adapter.NewFilmAdapter;
import com.arifcebe.favoritmovie.module.main.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainInterface {

    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.list_new_movie)
    RecyclerView recyclerView;
    @BindView(R.id.list_genre)
    RecyclerView recyclerViewGenre;

    private MainPresenter presenter;
    private List<Movie> movieList = new ArrayList<>();
    private List<Genre> genreList = new ArrayList<>();
    private GenreAdapter genreAdapter;
    private NewFilmAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        presenter = new MainPresenter(this,this);

        // set adapter and recyclerview for favorite film
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true);
        recyclerView.setLayoutManager(layoutManager);
        movieAdapter = new NewFilmAdapter(this,movieList);
        recyclerView.setAdapter(movieAdapter);

        // set adapter and recyclerview for genre movies
        recyclerViewGenre.setLayoutManager(new LinearLayoutManager(this));
        genreAdapter = new GenreAdapter(this,genreList);
        recyclerViewGenre.setAdapter(genreAdapter);

        presenter.fetchFavoriteMovie();
        presenter.fetchGenre();
    }

    @Override
    public void successLoadFavoriteMovie(List<Movie> movies) {
        for (Movie m : movies) {
            movieList.add(m);
        }
        movieAdapter.notifyDataSetChanged();
    }

    @Override
    public void failedLoadFavoriteMovie(int errorType) {

    }

    @Override
    public void successLoadGenre(List<Genre> genres) {
        for (Genre g : genres){
            genreList.add(g);
        }
        genreAdapter.notifyDataSetChanged();
    }

    @Override
    public void failedLoadGenre(int errorType) {

    }

    @Override
    public void clearData() {
        genreList.clear();
        movieList.clear();
    }

    @Override
    public void onLoading(boolean loading) {
        if (loading){
            AppProgressDialog.getInstance(this).showDialog(getString(R.string.load_data));
        } else{
            AppProgressDialog.getInstance(this).dissmissDialog();
        }
    }
}
