package com.example.android.popluarmovies;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.android.popluarmovies.data.StaredMoviesContract;
import com.example.android.popluarmovies.data.StaredMoviesReaderDbHelper;

import org.parceler.Parcels;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.Unbinder;

import static com.example.android.popluarmovies.R.id.listView;

public class MainFragment extends Fragment {

    public static final String URL_BASE = "http://api.themoviedb.org/3/movie/";
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    @BindView(listView)
    GridView list;
    ArrayList<Movie> movies;
    private MovieAdapter adpter;
    private Unbinder unbinder;

    public MainFragment() {
        // Required empty public constructor
    }

    @OnItemClick(listView)
    void onItemClick(int i) {
        Movie current = (Movie) list.getItemAtPosition(i);
        if (!MainActivity.twoPane) {
            startActivity(new Intent(getActivity(), MovieDetail.class).putExtra("movieID", current.getMovieID()));
        } else {
            ((Callback) getActivity()).onItemSelected(current.getMovieID());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void updateUi(ArrayList<Movie> movies) {
        adpter.clear();
        adpter.addAll(movies);
    }

    @Override
    public void onResume() {
        super.onResume();

        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (isConnected && !PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("sort", "popular").equals("favorites")) {
            QueryAsyncTask task = new QueryAsyncTask();
            task.execute();
        }
        if (PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("sort", "popular").equals("favorites")) {
            movies = new ArrayList<>();
            SQLiteOpenHelper dbHelper = new StaredMoviesReaderDbHelper(getActivity());
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor c = db.query(StaredMoviesContract.StaredMoviesColumns.TABLE_NAME,
                    null, null, null, null, null, null);
            if (c.moveToFirst()) {
                while (c.moveToNext()) {
                    movies.add(new Movie(c.getLong(c.getColumnIndex(StaredMoviesContract.StaredMoviesColumns.COLUMN_NAME_MOVIE_ID)),
                            c.getString(c.getColumnIndex(StaredMoviesContract.StaredMoviesColumns.COLUMN_NAME_MOVIE_POSTER))));
                }
            }
            c.close();
            updateUi(movies);
            adpter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        unbinder = ButterKnife.bind(this, view);

        adpter = new MovieAdapter(getActivity(), new ArrayList());
        if (list != null) {
            Log.v(LOG_TAG, "GridView found and set to non-null!");
            list.setAdapter(adpter);
        }
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();


        if (savedInstanceState == null || adpter.getCount() == 0) {
            if (isConnected && !PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("sort", "popular").equals("favorites")) {
                QueryAsyncTask task = new QueryAsyncTask();
                task.execute();
            }
            if (PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("sort", "popular").equals("favorites")) {
                List<Movie> movies = new ArrayList<>();
                SQLiteOpenHelper dbHelper = new StaredMoviesReaderDbHelper(getActivity());
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                Cursor c = db.query(StaredMoviesContract.StaredMoviesColumns.TABLE_NAME,
                        null, null, null, null, null, null);
                if (c.moveToFirst()) {
                    while (c.moveToNext()) {
                        movies.add(new Movie(c.getLong(c.getColumnIndex(StaredMoviesContract.StaredMoviesColumns.COLUMN_NAME_MOVIE_ID)),
                                c.getString(c.getColumnIndex(StaredMoviesContract.StaredMoviesColumns.COLUMN_NAME_MOVIE_POSTER))));
                    }
                }
                c.close();
            }
        } else {
            updateUiFromInstanceState(savedInstanceState);
        }

        return view;
    }

    private void updateUiFromInstanceState(Bundle IS) {
        movies = Parcels.unwrap(IS.getParcelable("movies"));
        updateUi(movies);
        adpter.notifyDataSetChanged();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("movies", Parcels.wrap(movies));
        super.onSaveInstanceState(outState);
    }

    public interface Callback {
        void onItemSelected(long movie);
    }

    private class QueryAsyncTask extends AsyncTask<URL, Void, ArrayList<Movie>> {

        @Override
        protected ArrayList<Movie> doInBackground(URL... urls) {
            URL url;
            url = createUrl(URL_BASE + PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("sort", "popular") + "?api_key=" + MainActivity.API_KEY);


            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Problem making the HTTP request.", e);
            }

//            Gson gson = new Gson();
//            gson.fromJson(jsonResponse, String.class);

            return QueryUtils.extractDetails(jsonResponse, true);
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            if (movies == null) {
                return;
            }
            MainFragment.this.movies = movies;
            updateUi(movies);
            adpter.notifyDataSetChanged();
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
