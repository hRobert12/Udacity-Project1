package com.example.android.popluarmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Robert on 9/26/2016.
 */
public class movieAdpter extends ArrayAdapter<movie> {

    public movieAdpter(Context context, List objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        ImageView image = (ImageView) convertView.findViewById(R.id.image);
        Picasso.with(getContext()).invalidate("http://image.tmdb.org/t/p/w185" + getItem(position).getMoviePosterPath());
        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w185" + getItem(position).getMoviePosterPath()).into(image);

        return convertView;
    }
}
