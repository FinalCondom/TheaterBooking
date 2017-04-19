package com.example.lucien.theaterbooking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lucien.theaterbooking.DatabaseObject.Movie;

import java.util.ArrayList;

/**
 * Created by Robert on 11.04.2017.
 */

public class MovieAdapter extends BaseAdapter {

    private ArrayList<Movie> movies;
    private LayoutInflater layoutInflater;

    public MovieAdapter(Context aContext, ArrayList<Movie> listData) {
        this.movies = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }


    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.activity_display_theater_row, null);
            holder = new ViewHolder();
            holder.titleView=(TextView) convertView.findViewById(R.id.movieTitle);
            holder.descView = (TextView) convertView.findViewById(R.id.movieDesc);
            holder.durationView = (TextView) convertView.findViewById(R.id.movieDuration);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.titleView.setText(movies.get(position).getTitle());
        holder.descView.setText(movies.get(position).getDescription());
        holder.durationView.setText(movies.get(position).getDuration());
        return convertView;
    }

    static class ViewHolder {
        TextView titleView;
        TextView descView;
        TextView durationView;
    }
}
