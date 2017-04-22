package com.example.lucien.theaterbooking;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lucien.theaterbooking.DatabaseObject.Movie;
import com.example.lucien.theaterbooking.DatabaseObject.Show;
import com.example.lucien.theaterbooking.DatabaseTable.DatabaseHandler;

import java.util.ArrayList;

/**
 * Created by lucien on 04.04.2017.
 */

public class DisplayMovieActivity extends AppCompatActivity {
    Movie m;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movie);

        DatabaseHandler db =new DatabaseHandler(this);

        final int id=getIntent().getIntExtra("movieId",0);

         m=db.getMovie(id);

        Log.d("Title",m.getTitle());
        TextView title=(TextView) findViewById(R.id.singleMovieTite);
        TextView desc=(TextView) findViewById(R.id.singleMovieDesc);
        TextView duration=(TextView) findViewById(R.id.singleMovieDur);


        title.setText(m.getTitle());
        desc.setText("Description: "+m.getDescription());
        duration.setText("Duration: "+m.getDuration());


        ArrayList<Show> allShow = (ArrayList<Show>) db.getShowsByMovie(id);
        final ListView lv1 = (ListView) findViewById(R.id.listShowing);
        lv1.setAdapter(new ScreeningAdapter(this, allShow));
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DisplayMovieActivity.this, ScreeningActivity.class);
                Show s = (Show) lv1.getItemAtPosition(position);
                intent.putExtra("movie",m.getTitle());
                intent.putExtra("showId", s.getIdShow());
                startActivity(intent);
            }
        });
    }
}
