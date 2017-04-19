package com.example.lucien.theaterbooking;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.lucien.theaterbooking.DatabaseObject.Movie;
import com.example.lucien.theaterbooking.DatabaseTable.DatabaseHandler;

/**
 * Created by lucien on 04.04.2017.
 */

public class DisplayMovieActivity extends AppCompatActivity {

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movie);

Log.d("Hello","world");

        int id=getIntent().getIntExtra("movieId",0);

        Movie m=DisplayTheaterActivity.db.getMovie(id);

        Log.d("Title",m.getTitle());
        TextView title=(TextView) findViewById(R.id.singleMovieTite);
        TextView desc=(TextView) findViewById(R.id.singleMovieDesc);
        TextView duration=(TextView) findViewById(R.id.singleMovieDur);

        title.setText(m.getTitle());
        desc.setText("Description: "+m.getDescription());
        duration.setText("Duration: "+m.getDuration());
    }
}
