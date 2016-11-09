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
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.popluarmovies.data.StaredMoviesContract;
import com.example.android.popluarmovies.data.StaredMoviesReaderDbHelper;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.json.JSONArray;
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

@EFragment
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
    @BindView(R.id.videos)
    ListView videoList;
    private long movieID;
    private String posterPath;
    private String jsonResponse;
    private SQLiteOpenHelper dbHelper;
    private SQLiteDatabase db;
    private Unbinder unbinder;
    private boolean isDone;
    private VideoAdapter videoAdapter;

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    public static MovieDetailFragment newInstance(long movieID) {
        MovieDetailFragment f = new MovieDetailFragment();

        Bundle args = new Bundle();
        args.putLong("movieID", movieID);
        f.setArguments(args);

        return f;
    }

    public long getShownMovie() {
        if (getArguments() != null) {
            return getArguments().getLong("movieID", -1);
        }
        return -1;
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
    }

    private void updateUi(ArrayList<Movie> movies) {

        String posterPath = null;
        String orgTitle = null;
        String overview = null;
        String releaseDateText = null;
        String voteAverageText = null;
        ArrayList<String> videos = new ArrayList<>();

        JSONObject currentMovie;
        try {
            currentMovie = new JSONObject(jsonResponse);
            JSONArray array = currentMovie.getJSONArray("array");
            JSONObject details = array.getJSONObject(0);
            posterPath = details.getString("poster_path");
            orgTitle = details.getString("original_title");
            overview = details.getString("overview");
            releaseDateText = details.getString("release_date");
            voteAverageText = details.getString("vote_average") + getString(R.string.stars);
            JSONObject vids = array.getJSONObject(2);
            JSONArray videoArray = vids.getJSONArray("results");
            for (int i = 0; i < videoArray.length(); i++) {
                videos.add("https://youtube.com/watch?v=" + videoArray.getJSONObject(i).getString("key"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (posterPath != null) {
            Picasso.with(getActivity()).load("http://image.tmdb.org/t/p/w185" + posterPath).into(poster);
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
        if (videos != null && !videos.isEmpty()) {
            videoAdapter = new VideoAdapter(getActivity(), videos);
            videoList.setAdapter(videoAdapter);
        }
    }

    public void updateUIFromInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.getString("posterPath") != null) {
            Picasso.with(getActivity()).load("http://image.tmdb.org/t/p/w185" + movieID).into(poster);
        }
        title.setText(savedInstanceState.getString("title"));
        plot.setText(savedInstanceState.getString("plot"));
        releaseDate.setText(savedInstanceState.getString("release_date"));
        voteAverage.setText(savedInstanceState.getString("vote_avarge"));
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
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        unbinder = ButterKnife.bind(this, view);

        dbHelper = new StaredMoviesReaderDbHelper(getActivity());
        db = dbHelper.getWritableDatabase();

        if (MainActivity.twoPane) {
            movieID = getShownMovie();
        } else {
            Intent receivedIntent = getActivity().getIntent();
            movieID = receivedIntent.getLongExtra("movieID", -1);
        }

        if (isMovieInDatabase(movieID) && movieID != -1) {
            FAB.setImageResource(R.drawable.ic_star_black_24dp);
        }

        if (savedInstanceState == null) {

            ConnectivityManager cm =
                    (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();

            if (isConnected) {
                if (movieID != -1) {
                    QueryAsyncTask task = new QueryAsyncTask();
                    task.execute(MainActivity.URL_BASE + movieID + "?api_key=" + MainActivity.API_KEY, MainActivity.URL_BASE + movieID + "/reviews" + "?api_key=" + MainActivity.API_KEY, MainActivity.URL_BASE + movieID + "/videos" + "?api_key=" + MainActivity.API_KEY);
                }
            }
        } else {
            updateUIFromInstanceState(savedInstanceState);
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putString("title", title.getText().toString());
        outState.putString("plot", plot.getText().toString());
        outState.putString("release_date", releaseDate.getText().toString());
        outState.putString("vote_avarge", voteAverage.getText().toString());
        outState.putString("posterPath", posterPath);

        super.onSaveInstanceState(outState);
    }

    @Background
    void myBackgrondStuff(String urlToRequest) {
        isDone = false;

        URL url;
        try {
            url = new URL(urlToRequest);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return;
        }

        String jsonResponse = "";
        try {
            if (url == null) {
                return;
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
                    jsonResponse = output.toString();
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
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        isDone = true;
    }

    private class QueryAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            URL url;
            URL url2;
            URL url3;
            url = createUrl(urls[0]);
            url2 = createUrl(urls[1]);
            url3 = createUrl(urls[2]);

            jsonResponse = "";
            try {
                jsonResponse = "{ \"array\":[" + makeHttpRequest(url) + ", " + makeHttpRequest(url2) + ", " + makeHttpRequest(url3) + "]}";
            } catch (IOException e) {
                Log.e(LOG_TAG, "Problem making the HTTP request.", e);
            }

            return jsonResponse;
        }

        @Override
        protected void onPostExecute(String movies) {
            if (movies == null) {
                return;
            }

            updateUi(QueryUtils.extractDetails(jsonResponse, false));
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
