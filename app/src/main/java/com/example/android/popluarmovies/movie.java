package com.example.android.popluarmovies;


public class Movie {

    private long mMovieID;
    private String mMoviePosterPath;

    public Movie(long ID, String poster) {
        mMovieID = ID;
        mMoviePosterPath = poster;
    }


    public long getMovieID() {
        return mMovieID;
    }

    public String getMoviePosterPath() {
        return mMoviePosterPath;
    }
}
