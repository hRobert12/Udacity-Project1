package com.example.android.popluarmovies;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

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

public class movieDetail extends AppCompatActivity {

    private static final String LOG_TAG = movieDetail.class.getSimpleName();
    private long movieID;
    private String jsonResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {actionBar.setDisplayHomeAsUpEnabled(true);}

        Intent receivedIntent = getIntent();
        movieID = receivedIntent.getLongExtra("movieID", -1);

        Log.i(LOG_TAG, "MovieID:" + movieID);

        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

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



    private class QueryAsyncTask extends AsyncTask<URL, Void, ArrayList<movie>> {

        @Override
        protected ArrayList<movie> doInBackground(URL... urls) {
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
        protected void onPostExecute(ArrayList<movie> movies) {
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
                Log.e(LOG_TAG, "Problem retrieving the movie JSON results.", e);
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

    private void updateUi(ArrayList<movie> movies) {
        ImageView poster = (ImageView) findViewById(R.id.poster);
        TextView title = (TextView) findViewById(R.id.title);
        TextView plot = (TextView) findViewById(R.id.plot);
        TextView releaseDate = (TextView) findViewById(R.id.release_date);
        TextView voteAverage = (TextView) findViewById(R.id.vote_average);

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
            voteAverageText = currentMovie.getString("vote_average") + " stars";
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (posterPath != null) {
            Picasso.with(this).invalidate("http://image.tmdb.org/t/p/w185" + posterPath);
            Picasso.with(this).load("http://image.tmdb.org/t/p/w185" + movies.get(0).getMoviePosterPath()).into(poster);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
