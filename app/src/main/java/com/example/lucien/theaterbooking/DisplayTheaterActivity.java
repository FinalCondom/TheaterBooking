package com.example.lucien.theaterbooking;

import android.content.Intent;
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

public class DisplayTheaterActivity extends AppCompatActivity {
        ListView mListView ;
    public static DatabaseHandler db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db= new DatabaseHandler(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_theater);
        DatabaseHandler db = new DatabaseHandler(this);
       setFirstData(db);

        ArrayList<Movie> allMovies=(ArrayList<Movie>) db.getAllMovies();
        final ListView lv1 = (ListView) findViewById(R.id.movie_list);
        lv1.setAdapter(new MovieAdapter(this, allMovies));
       lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent =new Intent(DisplayTheaterActivity.this,DisplayMovieActivity.class);
               Movie m = (Movie)lv1.getItemAtPosition(position);
               intent.putExtra("movieId",m.getIdMovie());
               startActivity(intent);
           }
       });

    }

    private void setFirstData(DatabaseHandler db) {
        Log.d("Insert", "Inserting actor");




        db.AddRoom(new Room("Arlequin", "", 334));

        //adding actor

        db.AddActor(new Actor("Keanu Reeves"));
        db.AddActor(new Actor("Laurence Fishburne"));
        db.AddActor( new Actor("Chris Pratt"));

        //adding movies
        db.AddMovie( new Movie("John Wick : Chapter 2", "John Wick est forcé de sortir de sa retraite volontaire par un de ses ex-associés qui cherche à prendre le contrôle d’une mystérieuse confrérie de tueurs internationaux. Parce qu’il est lié à cet homme par un serment, John se rend à Rome, " +
                "où il va devoir affronter certains des tueurs les plus dangereux du monde. ","122 minutes"));
        db.AddMovie(new Movie("Guardians of the Galaxy 2","Les Gardiens de la galaxie 2 poursuit les aventures de l'équipe alors qu'elle traverse les confins du cosmos. Les gardiens doivent combattre pour " +
                "rester unis alors qu'ils découvrent les mystères de la filiation de Peter Quill", "120 minutes"));

        //Adding room


        db.AddRoom(new Room("Lux", "dont 16 sièges VIP, boucle magnétique pour malentendants", 198));
        db.AddRoom(new Room("Capitole", "", 110));
        db.AddRoom(new Room("Les cèdres", "", 80));

        //Adding Actor in movie
        db.AddActorInMovie(db.getActor(1),db.getMovie(1));
        db.AddActorInMovie(db.getActor(2),db.getMovie(1));
        db.AddActorInMovie(db.getActor(3),db.getMovie(2));


        Room room=db.getRoom(2);
        Show jw1 = new Show(room.getIdRoom(), db.getMovie(1).getIdMovie(), 20, room.getNumberSeats(), "Jeudi 13 avril 2017 ");

        db.AddShow(jw1);

        db.AddBooking(new Booking(jw1.getIdShow(), "Hugo Rebelo", 1));

    }
}
