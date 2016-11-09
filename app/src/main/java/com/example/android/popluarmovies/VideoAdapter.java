package com.example.android.popluarmovies;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoAdapter extends ArrayAdapter<String> {

    public VideoAdapter(Context context, List objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.video_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.videoName.setText("Tralier " + position);
        holder.videoContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_WEB_SEARCH);
                webIntent.putExtra(SearchManager.QUERY, getItem(position));

            }
        });

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.name)
        TextView videoName;
        @BindView(R.id.video_container)
        LinearLayout videoContainer;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
