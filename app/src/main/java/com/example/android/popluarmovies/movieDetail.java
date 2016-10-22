package com.example.android.popluarmovies;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MovieDetail extends AppCompatActivity {

    private static final String LOG_TAG = MovieDetail.class.getSimpleName();
    private long movieID;
    private String posterPath;
    private String jsonResponse;
    private SQLiteOpenHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {actionBar.setDisplayHomeAsUpEnabled(true);}

        Intent receivedIntent = getIntent();

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
