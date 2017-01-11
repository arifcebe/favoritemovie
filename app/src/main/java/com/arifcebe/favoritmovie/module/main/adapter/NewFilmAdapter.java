package com.arifcebe.favoritmovie.module.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arifcebe.favoritmovie.R;
import com.arifcebe.favoritmovie.model.Movie;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by arifcebe
 * on Jan 1/10/17 14:29.
 * Project : FavoritMovie
 * <p>
 * Lebih Baik Pulang Nama, Daripada Gagal di Medan Laga
 */

public class NewFilmAdapter extends RecyclerView.Adapter<NewFilmAdapter.NewFilmHolder>{

    private List<Movie> popularMovies;
    private Context context;

    public NewFilmAdapter(Context context, List<Movie> popularMovies) {
        this.context = context;
        this.popularMovies = popularMovies;
    }

    @Override
    public NewFilmHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.new_movie_item,parent,false);

        return new NewFilmHolder(view);
    }

    @Override
    public void onBindViewHolder(NewFilmHolder holder, int position) {
        Movie _movie = popularMovies.get(position);
        holder.title.setText(_movie.getTitle());
        Glide.with(context)
                .load(_movie.getBackdropPath())
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return popularMovies.size();
    }

    static class NewFilmHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.new_movie_title)
        TextView title;
        @BindView(R.id.new_movie_img)
        ImageView img;

        public NewFilmHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
