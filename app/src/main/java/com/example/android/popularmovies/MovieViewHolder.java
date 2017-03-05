package com.example.android.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Will on 3/4/2017.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private ArrayList<Movie> mMovies;
    private Movie mMovie;

    public ImageView mMoviePoster;

    public MovieViewHolder(View itemView, ArrayList<Movie> movies) {
        super(itemView);

        itemView.setOnClickListener(this);
        mMovies = movies;

        mMoviePoster = (ImageView) itemView.findViewById(R.id.iv_movie_poster);
    }

    @Override
    public void onClick(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, DetailActivity.class);
        mMovie = mMovies.get(getAdapterPosition());

        intent.putExtra("title", mMovie.getTitle());
        intent.putExtra("movie_poster", mMovie.getMoviePoster());
        intent.putExtra("plot_synopsis", mMovie.getPlotSynopsis());
        intent.putExtra("movie_rating", mMovie.getMovieRating());
        intent.putExtra("release_date", mMovie.getReleaseDate());

        context.startActivity(intent);
    }
}
