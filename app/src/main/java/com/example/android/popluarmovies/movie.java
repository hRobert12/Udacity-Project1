package com.example.android.popluarmovies;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;
import org.parceler.ParcelProperty;

@Parcel
public class Movie {

    @ParcelProperty("ID")
    private long mMovieID;
    @ParcelProperty("poster")
    private String mMoviePosterPath;

    @ParcelConstructor
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
