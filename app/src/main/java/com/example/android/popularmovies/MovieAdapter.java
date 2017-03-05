package com.example.android.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Will on 3/4/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    private Context mContext;
    private ArrayList<Movie> mMovies;

    public MovieAdapter(Context context) {
        mContext = context;
    }

    public void setMovieData(ArrayList<Movie> movies) {
        mMovies = movies;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, null);
        MovieViewHolder holder = new MovieViewHolder(view, mMovies);

        return holder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Picasso.with(mContext).load(mMovies.get(position).getMoviePoster()).into(holder.mMoviePoster);
    }

    @Override
    public int getItemCount() {
        if (mMovies == null) {
            return 0;
        }

        return mMovies.size();
    }
}
