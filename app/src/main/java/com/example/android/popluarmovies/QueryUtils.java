package com.example.android.popluarmovies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QueryUtils {

    private QueryUtils() {}

    public static ArrayList<Movie> extractDetails(String json, boolean list) {
        ArrayList<Movie> movies = new ArrayList<>();
        try {
            if (list) {
                JSONObject root = new JSONObject(json);
                JSONArray items = root.getJSONArray("results");
                for (int i = 0; i < items.length(); i++) {
                    JSONObject currentMovie = items.getJSONObject(i);
                    String posterPath = currentMovie.getString("poster_path");
                    long movieID = currentMovie.getLong("id");
                    movies.add(new Movie(movieID, posterPath));
                }
            } else {
                JSONObject currentMovie = new JSONObject(json);
                String posterPath = currentMovie.getString("poster_path");
                long movieID = currentMovie.getLong("id");
                movies.add(new Movie(movieID, posterPath));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movies;
    }
}
