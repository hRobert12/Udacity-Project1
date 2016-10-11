package com.example.android.popluarmovies.data;

import android.provider.BaseColumns;

/**
 * Created by Robert on 10/8/2016.
 */
public class StaredMoviesContract {

    private StaredMoviesContract() {throw new AssertionError("No instances of StaredMoviesContract for you!");}

    public static abstract class StaredMoviesColumns implements BaseColumns {
        public static final String TABLE_NAME = "StaredMovies";
        public static final String COLUMN_NAME_MOVIE_ID = "movie_id";
        public static final String COLUMN_NAME_MOVIE_POSTER = "movie_poster";
    }

}
