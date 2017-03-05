package com.example.android.popularmovies;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    public static final int SPAN_COUNT = 2;
    private MovieAdapter mMovieAdapter;

    private OkHttpClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mClient = new OkHttpClient();
        new MovieLoaderTask().execute("https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=ceabeb8422b1125d2b21db4a043f59ac");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_most_popular:
                new MovieLoaderTask().execute("https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=ceabeb8422b1125d2b21db4a043f59ac");

                return true;
            case R.id.action_highest_rated:
                new MovieLoaderTask().execute("https://api.themoviedb.org/3/discover/movie?sort_by=vote_average.desc&api_key=ceabeb8422b1125d2b21db4a043f59ac");

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = mClient.newCall(request).execute();
        return response.body().string();
    }

    private class MovieLoaderTask extends AsyncTask<String, Void, ArrayList<Movie>> {

        @Override
        protected ArrayList<Movie> doInBackground(String... strings) {
            JSONObject response;
            JSONArray moviesJSON;
            JSONObject movieJSON;
            Movie movie = null;
            ArrayList<Movie> movies = null;

            try {
                response = new JSONObject(run(strings[0]));
                moviesJSON = response.getJSONArray("results");
                movies = new ArrayList<Movie>();

                for (int i = 0; i < moviesJSON.length(); i++) {
                    movieJSON = moviesJSON.getJSONObject(i);
                    movie = new Movie();
                    movie.setTitle(movieJSON.getString("title"));
                    movie.setMoviePoster("https://image.tmdb.org/t/p/w500/" + movieJSON.getString("poster_path").substring(1));
                    movie.setPlotSynopsis(movieJSON.getString("overview"));
                    movie.setMovieRating(movieJSON.getString("vote_average"));
                    movie.setReleaseDate(movieJSON.getString("release_date"));
                    movies.add(movie);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return movies;
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            super.onPostExecute(movies);
            if (movies != null) {
                mRecyclerView = (RecyclerView) findViewById(R.id.rv_movies);
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new GridLayoutManager(MainActivity.this, SPAN_COUNT);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mMovieAdapter = new MovieAdapter(MainActivity.this);
                mMovieAdapter.setMovieData(movies);
                mRecyclerView.setAdapter(mMovieAdapter);
            }
        }
    }
}
