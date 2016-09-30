package com.example.android.popluarmovies;

import android.content.Intent;

/**
 * Created by Robert on 9/26/2016.
 */
public class movie {

    private String mMovieID;
    private String mMoviePosterPath;

    public movie(String ID, String poster) {
        mMovieID = ID;
        mMoviePosterPath = poster;
    }


    public String getMovieID() {
        return mMovieID;
    }

    public String getMoviePosterPath() {
        return mMoviePosterPath;
    }
}
