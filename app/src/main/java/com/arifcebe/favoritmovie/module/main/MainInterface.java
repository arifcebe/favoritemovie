package com.arifcebe.favoritmovie.module.main;

import com.arifcebe.favoritmovie.base.BasePresenter;
import com.arifcebe.favoritmovie.model.Genre;
import com.arifcebe.favoritmovie.model.Movie;

import java.util.List;

/**
 * Created by arifcebe
 * on Jan 1/11/17 11:31.
 * Project : FavoritMovie
 * <p>
 * Lebih Baik Pulang Nama, Daripada Gagal di Medan Laga
 */

public interface MainInterface extends BasePresenter {

    void successLoadFavoriteMovie(List<Movie> movies);

    void failedLoadFavoriteMovie(int errorType);

    void successLoadGenre(List<Genre> genres);

    void failedLoadGenre(int errorType);

    void clearData();

}
