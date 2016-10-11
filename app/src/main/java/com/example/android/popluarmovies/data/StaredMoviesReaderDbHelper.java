package com.example.android.popluarmovies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Robert on 10/8/2016.
 */
public class StaredMoviesReaderDbHelper extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + StaredMoviesContract.StaredMoviesColumns.TABLE_NAME + " (" +
            StaredMoviesContract.StaredMoviesColumns._ID + " INTEGER PRIMARY KEY," +
            StaredMoviesContract.StaredMoviesColumns.COLUMN_NAME_MOVIE_ID + INTEGER_TYPE + COMMA_SEP +
            StaredMoviesContract.StaredMoviesColumns.COLUMN_NAME_MOVIE_POSTER + TEXT_TYPE +
            " )";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + StaredMoviesContract.StaredMoviesColumns.TABLE_NAME;
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "StaredMoviesReader.db";

    private Context mContext;

    public StaredMoviesReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
