package com.arifcebe.favoritmovie.module;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arifcebe.favoritmovie.R;
import com.arifcebe.favoritmovie.model.Movie;
import com.arifcebe.favoritmovie.rest.ApiClient;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by arifcebe
 * on Jan 1/10/17 11:39.
 * Project : FavoritMovie
 * <p>
 * Lebih Baik Pulang Nama, Daripada Gagal di Medan Laga
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movies;
    private int rowLayout;
    private Context context;

    public MovieAdapter(List<Movie> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(rowLayout, null);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie _movie = movies.get(position);
        holder.movieTitle.setText(_movie.getTitle());
        holder.movieDescription.setText(_movie.getOverview());
        holder.rating.setText(String.valueOf(_movie.getVoteAverage()));
        holder.data.setText(_movie.getReleaseDate());

        Picasso.with(context)
                .load(ApiClient.IMAGE_PATH + _movie.getPosterPath())
                .into(holder.imgPreview);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.subtitle)
        TextView data;
        @BindView(R.id.movies_layout)
        LinearLayout moviesLayout;
        @BindView(R.id.title)
        TextView movieTitle;
        @BindView(R.id.description)
        TextView movieDescription;
        @BindView(R.id.rating)
        TextView rating;
        @BindView(R.id.image_preview)
        ImageView imgPreview;

        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}


