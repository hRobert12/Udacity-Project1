package com.example.android.popluarmovies;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewAdapter extends ArrayAdapter<Bundle> {

    public ReviewAdapter(Context context, List objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.review_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Bundle passedBundle = getItem(position);

        holder.author.setText(passedBundle.getString("author") + " wrote:");

        holder.contents.setText(passedBundle.getString("content"));


        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.author)
        TextView author;
        @BindView(R.id.review_contents)
        TextView contents;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
