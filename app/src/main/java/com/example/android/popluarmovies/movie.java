package com.example.android.popluarmovies;


public class movie {

    private long mMovieID;
    private String mMoviePosterPath;

    public movie(long ID, String poster) {
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
