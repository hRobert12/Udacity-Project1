package com.example.android.popluarmovies;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popluarmovies.data.StaredMoviesContract;
import com.example.android.popluarmovies.data.StaredMoviesReaderDbHelper;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MovieDetailFragment extends Fragment {

    private static final String LOG_TAG = MovieDetail.class.getSimpleName();
    @BindView(R.id.poster)
    ImageView poster;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.plot)
    TextView plot;
    @BindView(R.id.release_date)
    TextView releaseDate;
    @BindView(R.id.vote_average)
    TextView voteAverage;
    @BindView(R.id.fab)
    FloatingActionButton FAB;
    private long movieID;
    private String posterPath;
    private String jsonResponse;
    private SQLiteOpenHelper dbHelper;
    private SQLiteDatabase db;
    private Unbinder unbinder;

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    @OnClick(R.id.fab)
    public void onFABClick() {
        if (isMovieInDatabase(movieID)) {
            int rows = db.delete(StaredMoviesContract.StaredMoviesColumns.TABLE_NAME, StaredMoviesContract.StaredMoviesColumns.COLUMN_NAME_MOVIE_ID + " IS " + movieID, null);

            if (rows > 0) {
                FAB.setImageResource(R.drawable.ic_star_border_black_24dp);
            }
        } else {

            ContentValues values = new ContentValues();
            values.put(StaredMoviesContract.StaredMoviesColumns.COLUMN_NAME_MOVIE_ID, movieID);
            values.put(StaredMoviesContract.StaredMoviesColumns.COLUMN_NAME_MOVIE_POSTER, posterPath);
            long rowID = db.insert(StaredMoviesContract.StaredMoviesColumns.TABLE_NAME, null, values);

            if (rowID != -1) {
                FAB.setImageResource(R.drawable.ic_star_black_24dp);
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        unbinder = ButterKnife.bind(getActivity());

        dbHelper = new StaredMoviesReaderDbHelper(getActivity());
        db = dbHelper.getWritableDatabase();

//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {actionBar.setDisplayHomeAsUpEnabled(true);}

        Intent receivedIntent = getActivity().getIntent();
        movieID = receivedIntent.getLongExtra("movieID", -1);

        if (isMovieInDatabase(movieID)) {
            FAB.setImageResource(R.drawable.ic_star_black_24dp);
        }

        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (isConnected) {
            if (movieID != -1) {
                QueryAsyncTask task = new QueryAsyncTask();
                task.execute();
            }
        }
    }

    private void updateUi(ArrayList<Movie> movies) {

        String posterPath = null;
        String orgTitle = null;
        String overview = null;
        String releaseDateText = null;
        String voteAverageText = null;

        JSONObject currentMovie;
        try {
            currentMovie = new JSONObject(jsonResponse);
            posterPath = currentMovie.getString("poster_path");
            orgTitle = currentMovie.getString("original_title");
            overview = currentMovie.getString("overview");
            releaseDateText = currentMovie.getString("release_date");
            voteAverageText = currentMovie.getString("vote_average") + getString(R.string.stars);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (posterPath != null) {
            Picasso.with(getActivity()).load("http://image.tmdb.org/t/p/w185" + movies.get(0).getMoviePosterPath()).into(poster);
            this.posterPath = posterPath;
        }
        if (orgTitle != null) {
            title.setText(orgTitle);
        }
        if (overview != null) {
            plot.setText(overview);
        }
        if (releaseDateText != null) {
            releaseDate.setText(releaseDateText);
        }
        if (voteAverageText != null) {
            voteAverage.setText(voteAverageText);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                NavUtils.navigateUpFromSameTask(getActivity());
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    private boolean isMovieInDatabase(long movieID) {
        Cursor c = db.query(StaredMoviesContract.StaredMoviesColumns.TABLE_NAME,
                new String[]{StaredMoviesContract.StaredMoviesColumns.COLUMN_NAME_MOVIE_POSTER,
                        StaredMoviesContract.StaredMoviesColumns.COLUMN_NAME_MOVIE_ID},
                null, null, null, null, null);

        if (c.moveToFirst()) {
            while (c.moveToNext()) {
                if (c.getLong(c.getColumnIndex(StaredMoviesContract.StaredMoviesColumns.COLUMN_NAME_MOVIE_ID)) == movieID) {
                    return true;
                }
            }
        }
        c.close();
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private class QueryAsyncTask extends AsyncTask<URL, Void, ArrayList<Movie>> {

        @Override
        protected ArrayList<Movie> doInBackground(URL... urls) {
            URL url;
            url = createUrl(MainActivity.URL_BASE + movieID + "?api_key=" + MainActivity.API_KEY);


            jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Problem making the HTTP request.", e);
            }

            return QueryUtils.extractDetails(jsonResponse, false);
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            if (movies == null) {
                return;
            }

            updateUi(movies);
        }

        private URL createUrl(String stringUrl) {
            URL url;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException exception) {
                Log.e(LOG_TAG, "Error with creating URL", exception);
                return null;
            }
            return url;
        }

        private String makeHttpRequest(URL url) throws IOException {
            String jsonResponse = "";

            // If the URL is null, then return early.
            if (url == null) {
                return jsonResponse;
            }

            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.connect();

                // If the request was successful (response code 200),
                // then read the input stream and parse the response.
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = urlConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);
                } else {
                    Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, "Problem retrieving the Movie JSON results.", e);
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    // function must handle java.io.IOException here
                    inputStream.close();
                }
            }
            return jsonResponse;
        }

        private String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }
    }
}
