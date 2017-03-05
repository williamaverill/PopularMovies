package com.example.android.popularmovies;

/**
 * Created by Will on 3/4/2017.
 */

public class Movie {
    private String mTitle;
    private String mMoviePoster;
    private String mPlotSynopsis;
    private String mMovieRating;
    private String mReleaseDate;

    public Movie() {

    }

    public String getTitle() {
        return mTitle;
    }

    public String getMoviePoster() {
        return mMoviePoster;
    }

    public String getPlotSynopsis() {
        return mPlotSynopsis;
    }

    public String getMovieRating() {
        return mMovieRating;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setMoviePoster(String moviePoster) {
        mMoviePoster = moviePoster;
    }

    public void setPlotSynopsis(String plotSynopsis) {
        mPlotSynopsis = plotSynopsis;
    }

    public void setMovieRating(String movieRating) {
        mMovieRating = movieRating;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }
}
