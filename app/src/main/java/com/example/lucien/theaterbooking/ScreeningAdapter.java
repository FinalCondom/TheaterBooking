package com.example.lucien.theaterbooking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lucien.theaterbooking.DatabaseObject.Show;
import com.example.lucien.theaterbooking.DatabaseTable.DatabaseHandler;

import java.util.ArrayList;

/**
 * Created by Robert on 20.04.2017.
 */

public class ScreeningAdapter extends BaseAdapter {

    private ArrayList<Show>shows;
    private LayoutInflater layoutInflater;
    private DatabaseHandler db;
    public ScreeningAdapter(Context aContext, ArrayList<Show> listData) {
        this.shows = listData;
        layoutInflater = LayoutInflater.from(aContext);
        db=new DatabaseHandler(aContext);
    }

    @Override
    public int getCount() {
        return shows.size();
    }

    @Override
    public Object getItem(int position) {
        return shows.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.activity_display_show_row, null);
            holder = new ViewHolder();
            holder.roomView=(TextView) convertView.findViewById(R.id.show_room);
            holder.priceView = (TextView) convertView.findViewById(R.id.show_price);
            holder.seatsView = (TextView) convertView.findViewById(R.id.show_seats);
            holder.dateView = (TextView) convertView.findViewById(R.id.show_date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.roomView.setText(db.getRoom(shows.get(position).getIdRoom()).getName());
        holder.priceView.setText(String.valueOf(shows.get(position).getPrice()));
        holder.seatsView.setText(String.valueOf(shows.get(position).getRemainingSeat()));
        holder.dateView.setText(shows.get(position).getDate());

        return convertView;
    }

    static class ViewHolder {
        TextView roomView;
        TextView priceView;
        TextView seatsView;
        TextView dateView;


    }
}
