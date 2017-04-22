package com.example.lucien.theaterbooking;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lucien.theaterbooking.DatabaseObject.Actor;
import com.example.lucien.theaterbooking.DatabaseObject.Booking;
import com.example.lucien.theaterbooking.DatabaseObject.Movie;
import com.example.lucien.theaterbooking.DatabaseObject.Room;
import com.example.lucien.theaterbooking.DatabaseObject.Show;
import com.example.lucien.theaterbooking.DatabaseTable.DatabaseHandler;

import java.util.ArrayList;
import java.util.Date;

public class DisplayTheaterActivity extends AppCompatActivity {
    ListView mListView;
   DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new DatabaseHandler(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_theater);
        DatabaseHandler db = new DatabaseHandler(this);
        //setFirstData(db);

        ArrayList<Movie> allMovies = (ArrayList<Movie>) db.getAllMovies();
        final ListView lv1 = (ListView) findViewById(R.id.movie_list);
        lv1.setAdapter(new MovieAdapter(this, allMovies));
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DisplayTheaterActivity.this, DisplayMovieActivity.class);
                Movie m = (Movie) lv1.getItemAtPosition(position);
                intent.putExtra("movieId", m.getIdMovie());
                startActivity(intent);
            }
        });

    }

    private void setFirstData(DatabaseHandler db) {


        //Creating rooms
        db.AddRoom(new Room("Arlequin", "", 334));
        db.AddRoom(new Room("Lux", "dont 16 sièges VIP, boucle magnétique pour malentendants", 198));
        db.AddRoom(new Room("Capitole", "", 110));
        db.AddRoom(new Room("Les Cèdres", "", 80));

        //adding actor
        db.AddActor(new Actor("Keanu Reeves")); //1
        db.AddActor(new Actor("Laurence Fishburne"));//2
        db.AddActor(new Actor("Chris Pratt"));//3
        db.AddActor(new Actor("Vin Diesel"));//4
        db.AddActor(new Actor("Dwayne Johnson"));//5

        //adding movies
        //1
        db.AddMovie(new Movie("John Wick : Chapter 2", getString(R.string.johnWick_desc), "122 minutes"));
        //2
        db.AddMovie(new Movie("Guardians of the Galaxy 2", getString(R.string.guardiansOfGal2_desc), "120 minutes"));
        //3
        db.AddMovie(new Movie("Fast 8",getString(R.string.fast8_desc),"136 minutes"));


        //Adding Actor in movie
        //John Wick 2
        db.AddActorInMovie(db.getActor(1), db.getMovie(1));
        db.AddActorInMovie(db.getActor(2), db.getMovie(1));

        //Guardians of the Galaxy 2
        db.AddActorInMovie(db.getActor(3), db.getMovie(2));

        //Fast 8
        db.AddActorInMovie(db.getActor(4),db.getMovie(3));
        db.AddActorInMovie(db.getActor(5),db.getMovie(3));


        //John Wick 2
        db.AddShow(new Show( db.getRoom(2).getIdRoom(), db.getMovie(1).getIdMovie(), 20, db.getRoom(2).getNumberSeats(), "13/04/2017 - 20H00 "));
        db.AddShow(new Show( db.getRoom(2).getIdRoom(), db.getMovie(1).getIdMovie(), 20, db.getRoom(2).getNumberSeats(), "14/04/2017 - 20H00 "));
        db.AddShow(new Show( db.getRoom(1).getIdRoom(), db.getMovie(1).getIdMovie(), 20, db.getRoom(1).getNumberSeats(), "16/04/2017 - 18H00 "));

        //Guardians of the galaxy 2
        db.AddShow(new Show( db.getRoom(1).getIdRoom(), db.getMovie(2).getIdMovie(), 22, db.getRoom(1).getNumberSeats(), "26/04/2017 - 20H30 "));
        db.AddShow(new Show( db.getRoom(1).getIdRoom(), db.getMovie(2).getIdMovie(), 22, db.getRoom(1).getNumberSeats(), "27/04/2017 - 20H30 "));
        db.AddShow(new Show( db.getRoom(1).getIdRoom(), db.getMovie(2).getIdMovie(), 22, db.getRoom(1).getNumberSeats(), "28/04/2017 - 20H30 "));
        db.AddShow(new Show( db.getRoom(3).getIdRoom(), db.getMovie(2).getIdMovie(), 22, db.getRoom(3).getNumberSeats(), "28/04/2017 - 16H30 "));
        db.AddShow(new Show( db.getRoom(1).getIdRoom(), db.getMovie(2).getIdMovie(), 22, db.getRoom(1).getNumberSeats(), "29/04/2017 - 20H30 "));

        //Fast 8
        db.AddShow(new Show( db.getRoom(4).getIdRoom(), db.getMovie(3).getIdMovie(), 20, db.getRoom(4).getNumberSeats(), "14/04/2017 - 18H00 "));
        db.AddShow(new Show( db.getRoom(4).getIdRoom(), db.getMovie(3).getIdMovie(), 20, db.getRoom(4).getNumberSeats(), "15/04/2017 - 18H00 "));
        db.AddShow(new Show( db.getRoom(4).getIdRoom(), db.getMovie(3).getIdMovie(), 20, db.getRoom(4).getNumberSeats(), "16/04/2017 - 18H00 "));
        db.AddShow(new Show( db.getRoom(4).getIdRoom(), db.getMovie(3).getIdMovie(), 20, db.getRoom(4).getNumberSeats(), "17/04/2017 - 18H00 "));






    }
}
