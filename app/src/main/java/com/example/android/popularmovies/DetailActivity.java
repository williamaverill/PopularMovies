package com.example.android.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private ImageView mMoviePoster;
    private TextView mRating;
    private TextView mReleaseDate;
    private TextView mPlotSynopsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();

        if (getSupportActionBar() != null && bundle.getString("title") != null)
            getSupportActionBar().setTitle(bundle.getString("title"));

        mMoviePoster = (ImageView) findViewById(R.id.iv_movie_poster);

        if (bundle.getString("movie_poster") != null) {
            Picasso.with(DetailActivity.this).load(bundle.getString("movie_poster")).into(mMoviePoster);
        }

        mRating = (TextView) findViewById(R.id.tv_rating);

        if (bundle.getString("movie_rating") != null) {
            mRating.setText(bundle.getString("movie_rating"));
        }

        mReleaseDate = (TextView) findViewById(R.id.tv_release_date);

        if (bundle.getString("release_date") != null) {
            mReleaseDate.setText(bundle.getString("release_date").split("-")[0]);
        }

        mPlotSynopsis = (TextView) findViewById(R.id.tv_plot_synopsis);

        if (bundle.getString("plot_synopsis") != null) {
            mPlotSynopsis.setText(bundle.getString("plot_synopsis"));
        }
    }
}
