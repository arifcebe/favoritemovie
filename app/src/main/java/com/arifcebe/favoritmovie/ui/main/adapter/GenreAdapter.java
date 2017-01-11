package com.arifcebe.favoritmovie.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arifcebe.favoritmovie.R;
import com.arifcebe.favoritmovie.model.Genre;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by arifcebe
 * on Jan 1/10/17 16:03.
 * Project : FavoritMovie
 * <p>
 * Lebih Baik Pulang Nama, Daripada Gagal di Medan Laga
 */

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder>{

    private Context context;
    private List<Genre> genreList;

    public GenreAdapter(Context context, List<Genre> genreList) {
        this.context = context;
        this.genreList = genreList;
    }

    @Override
    public GenreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.genre_item, parent,false);

        return new GenreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GenreViewHolder holder, int position) {
        holder.genreName.setText(genreList.get(position).getName());

        holder.genreName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return genreList.size();
    }

    static class GenreViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.genre_name)
        TextView genreName;

        public GenreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

