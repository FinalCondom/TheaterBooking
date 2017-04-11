package com.example.lucien.theaterbooking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lucien.theaterbooking.DatabaseObject.Actor;
import com.example.lucien.theaterbooking.DatabaseObject.Booking;
import com.example.lucien.theaterbooking.DatabaseObject.Movie;
import com.example.lucien.theaterbooking.DatabaseObject.Room;
import com.example.lucien.theaterbooking.DatabaseObject.Show;
import com.example.lucien.theaterbooking.DatabaseTable.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class DisplayTheaterActivity extends AppCompatActivity {
        ListView mListView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_theater);
        DatabaseHandler db = new DatabaseHandler(this);
        mListView = (ListView) findViewById(R.id.listView);



        //  setFirstData(db);



        /*Show tempShow = db.getShow(1);
        Log.d("Idmovie",String.valueOf(tempShow.getIdMovie()));
        Movie tempMove = db.getMovie(tempShow.getIdMovie());
        Actor tempAct=db.getActor(1);


        Booking tempbook = db.getBooking(1);

        TextView test = (TextView) findViewById(R.id.displayname);
        test.setText(tempShow.getDate() + " / " + tempbook.getClientName()+" / "+ tempMove.getDescription()+" / "+tempAct.getActorName());*/
    }

    private void setFirstData(DatabaseHandler db) {
        Log.d("Insert", "Inserting actor");

        Room arlequin = new Room("Arlequin", "", 334);




        //adding actor

        db.AddActor(new Actor("Keanu Reeves"));
        db.AddActor(new Actor("Laurence Fishburne"));
        db.AddActor( new Actor("Chris Pratt"));

        //adding movies
        db.AddMovie( new Movie("John Wick : Chapter 2", "122 minutes"));
        db.AddMovie(new Movie("Guardians of the Galaxy 2", "120 minutes"));

        //Adding room

        db.AddRoom(arlequin);
        db.AddRoom(new Room("Lux", "dont 16 sièges VIP, boucle magnétique pour malentendants", 198));
        db.AddRoom(new Room("Capitole", "", 110));
        db.AddRoom(new Room("Les cèdres", "", 80));

        //Adding Actor in movie
        db.AddActorInMovie(db.getActor(1),db.getMovie(1));
        db.AddActorInMovie(db.getActor(2),db.getMovie(1));
        db.AddActorInMovie(db.getActor(3),db.getMovie(2));

        Show jw1 = new Show(arlequin.getIdRoom(), db.getMovie(1).getIdMovie(), 20, arlequin.getNumberSeats(), "Jeudi 13 avril 2017 ");

        db.AddShow(jw1);

        db.AddBooking(new Booking(jw1.getIdShow(), "Hugo Rebelo", 1));

    }
}
