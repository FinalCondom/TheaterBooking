package com.example.lucien.theaterbooking.DatabaseTable;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.lucien.theaterbooking.DatabaseObject.Actor;
import com.example.lucien.theaterbooking.DatabaseObject.Booking;
import com.example.lucien.theaterbooking.DatabaseObject.Movie;
import com.example.lucien.theaterbooking.DatabaseObject.Room;
import com.example.lucien.theaterbooking.DatabaseObject.Show;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 04.04.2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TheaterManager";
    private static final int DATABASE_VER = 1;

    //Table names






    //Table Actor Movie


    //Table room


    //Table Show



    //Table Booking

    private static final String ID_BOOKING = "IdBooking";
    private static final String BOOKING_SHOW = "IdShow";
    private static final String BOOKING_CLIENT = "ClientName";
    private static final String BOOKING_NOSEATS = "BookedSeats";


    //Table Scripts
    //--------Create Table Script------
    private static final String CREATE_ACTOR_BDD = "CREATE TABLE " + TheaterContracts.Actors.TABLE_ACTORS + " (" +
            TheaterContracts.Actors.ID_ACTOR + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TheaterContracts.Actors.ACTORNAME + " TEXT NOT NULL);";

    private static final String CREATE_MOVIE_BDD = "CREATE TABLE " + TheaterContracts.Movies.TABLE_MOVIES + " (" +
            TheaterContracts.Movies.ID_MOVIE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TheaterContracts.Movies.MOVIE_TITLE+ " TEXT NOT NULL," +TheaterContracts.Movies.MOVIE_DESC + " TEXT NOT NULL," + TheaterContracts.Movies.MOVIE_DUR + " TEXT NOT NULL);";

    private static final String CREATE_ACTOR_MOVIE_BDD =
            "CREATE TABLE " + TheaterContracts.FilmActor.TABLE_ACTOR_MOVIE +
                    " (" + TheaterContracts.FilmActor.ID_ACTOR_MOVIE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "" + TheaterContracts.FilmActor.FILM_ACTOR + " INTEGER NOT NULL, " +
                    " " + TheaterContracts.FilmActor.FILM_MOVIE + " INTEGER NOT NULL, " +
                    "FOREIGN KEY(" + TheaterContracts.FilmActor.FILM_ACTOR + ") REFERENCES " + TheaterContracts.Actors.TABLE_ACTORS + "(" + TheaterContracts.Actors.ID_ACTOR + "), " +
                    "FOREIGN KEY(" + TheaterContracts.FilmActor.FILM_MOVIE + ") REFERENCES " + TheaterContracts.Movies.TABLE_MOVIES + "(" + TheaterContracts.Movies.ID_MOVIE + "));";

    private static final String CREATE_ROOM_BDD = "CREATE TABLE " + TheaterContracts.Rooms.TABLE_ROOM + " (" +
            TheaterContracts.Rooms.ID_ROOM + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TheaterContracts.Rooms.ROOM_NAME + " TEXT NOT NULL, " + TheaterContracts.Rooms.ROOM_DESC + " TEXT NOT NULL, " + TheaterContracts.Rooms.ROOM_SEATS +
            " INTEGER NOT NULL" + ");";

    private static final String CREATE_SHOW_BDD = "CREATE TABLE " + TheaterContracts.Shows.TABLE_SHOW + " (" +
            TheaterContracts.Shows.ID_SHOW + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TheaterContracts.Shows.SHOW_ROOM + " INTEGER, " +
            TheaterContracts.Shows.SHOW_MOVIE + " INTEGER, " + TheaterContracts.Shows.SHOW_PRICE + " DECIMAL NOT NULL, " +
            TheaterContracts.Shows.SHOW_REMAIN_SEAT + " INTEGER NOT NULL, " + TheaterContracts.Shows.SHOW_DATE + " TEXT NOT NULL, " +

            "FOREIGN KEY(" + TheaterContracts.Shows.SHOW_ROOM + ") REFERENCES " + TheaterContracts.Rooms.TABLE_ROOM + "(" + TheaterContracts.Rooms.ID_ROOM + "), " +
            "FOREIGN KEY(" + TheaterContracts.Shows.SHOW_MOVIE + ") REFERENCES " + TheaterContracts.Movies.TABLE_MOVIES + "(" + TheaterContracts.Movies.ID_MOVIE + "));";

    private static final String CREATE_BOOKING_BDD = "CREATE TABLE " + TheaterContracts.Bookings.TABLE_BOOKING + " (" +
            ID_BOOKING + " INTEGER PRIMARY KEY AUTOINCREMENT, " + BOOKING_SHOW + " INTEGER, " +
            BOOKING_CLIENT + " TEXT NOT NULL, " + BOOKING_NOSEATS + " INTEGER NOT NULL, " +
            "FOREIGN KEY(" + BOOKING_SHOW + ") REFERENCES " + TheaterContracts.Shows.TABLE_SHOW + "(" + TheaterContracts.Shows.ID_SHOW + "));";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_ACTOR_BDD);
        db.execSQL(CREATE_MOVIE_BDD);
        db.execSQL(CREATE_ACTOR_MOVIE_BDD);
        db.execSQL(CREATE_ROOM_BDD);
        db.execSQL(CREATE_SHOW_BDD);
        db.execSQL(CREATE_BOOKING_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TheaterContracts.Actors.TABLE_ACTORS + ", " + TheaterContracts.Movies.TABLE_MOVIES + ", " + TheaterContracts.FilmActor.TABLE_ACTOR_MOVIE + ", " +
                TheaterContracts.Shows.TABLE_SHOW + ", " + TheaterContracts.Bookings.TABLE_BOOKING + ", " + TheaterContracts.Rooms.TABLE_ROOM + ";");
        onCreate(db);
    }

    //All METHODS FOR ACTORS

    public void AddActor(Actor actor) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TheaterContracts.Actors.ACTORNAME, actor.getActorName());

        // Inserting Row
        db.insert(TheaterContracts.Actors.TABLE_ACTORS, null, values);
        db.close(); // Closing database connection
    }

    public Actor getActor(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TheaterContracts.Actors.TABLE_ACTORS, new String[]{TheaterContracts.Actors.ID_ACTOR,
                        TheaterContracts.Actors.ACTORNAME}, TheaterContracts.Actors.ID_ACTOR + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Actor actor = new Actor(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));
        // return contact
        return actor;
    }

    public List<Actor> getAllActor() {
        List<Actor> actorList = new ArrayList<Actor>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TheaterContracts.Actors.TABLE_ACTORS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                Actor actor = new Actor();
                actor.setIdActor(Integer.parseInt(cursor.getString(0)));
                actor.setActorName(cursor.getString(1));
                // Adding contact to list
                actorList.add(actor);
            } while (cursor.moveToNext());
        }

        // return contact list
        return actorList;

    }

    public int updateActor(Actor actor) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TheaterContracts.Actors.ACTORNAME, actor.getActorName());


        // updating row
        return db.update(TheaterContracts.Actors.TABLE_ACTORS, values, TheaterContracts.Actors.ID_ACTOR + " = ?",
                new String[]{String.valueOf(actor.getIdActor())});
    }


    public void deleteActor(Actor actor) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TheaterContracts.Actors.TABLE_ACTORS, TheaterContracts.Actors.ID_ACTOR + " = ?",
                new String[]{String.valueOf(actor.getIdActor())});
        db.close();
    }

    //ALL METHODS FOR MOVIE

    public void AddMovie(Movie movie) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TheaterContracts.Movies.MOVIE_DESC, movie.getDescription());
        values.put(TheaterContracts.Movies.MOVIE_TITLE,movie.getTitle());
        values.put(TheaterContracts.Movies.MOVIE_DUR, movie.getDuration());

        // Inserting Row
        db.insert(TheaterContracts.Movies.TABLE_MOVIES, null, values);
        db.close(); // Closing database connection
    }

    public Movie getMovie(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TheaterContracts.Movies.TABLE_MOVIES, new String[]{TheaterContracts.Movies.ID_MOVIE,TheaterContracts.Movies.MOVIE_TITLE,
                        TheaterContracts.Movies.MOVIE_DESC, TheaterContracts.Movies.MOVIE_DUR}, TheaterContracts.Movies.ID_MOVIE + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Movie movie = new Movie(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3));
        // return movie
        return movie;
    }

    public List<Movie> getAllMovies() {
        List<Movie> movieList = new ArrayList<Movie>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TheaterContracts.Movies.TABLE_MOVIES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                Movie movie = new Movie();
                movie.setIdMovie(Integer.parseInt(cursor.getString(0)));
                movie.setTitle(cursor.getString(1));
                movie.setDescription(cursor.getString(2));
                movie.setDuration(cursor.getString(3));
                // Adding contact to list
                movieList.add(movie);
            } while (cursor.moveToNext());
        }

        // return movie list
        return movieList;

    }

    //ALL METHODS FOR MOVIE ACTOR

    public void AddActorInMovie(Actor actor, Movie movie) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TheaterContracts.FilmActor.FILM_ACTOR, actor.getIdActor());
        values.put(TheaterContracts.FilmActor.FILM_MOVIE, movie.getIdMovie());

        // Inserting Row
        db.insert(TheaterContracts.FilmActor.TABLE_ACTOR_MOVIE, null, values);

        db.close(); // Closing database connection
    }

    public ArrayList<Actor> getAllActorInMovie(int ident) {
        ArrayList<Actor> actorList = new ArrayList<Actor>();
        // Select All Query
        String selectQuery = "SELECT " + TheaterContracts.FilmActor.FILM_ACTOR + " FROM " + TheaterContracts.FilmActor.TABLE_ACTOR_MOVIE +
                " WHERE " + TheaterContracts.FilmActor.FILM_MOVIE + "=" + ident + "; ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                // Adding contact to list
                actorList.add(getActor(Integer.parseInt(cursor.getString(0))));
            } while (cursor.moveToNext());
        }

        // return movie list
        return actorList;

    }
    //All METHODS FOR ROOM

    public void AddRoom(Room room) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TheaterContracts.Rooms.ROOM_DESC, room.getDescription());
        values.put(TheaterContracts.Rooms.ROOM_NAME, room.getName());
        values.put(TheaterContracts.Rooms.ROOM_SEATS, room.getNumberSeats());

        // Inserting Row
        db.insert(TheaterContracts.Rooms.TABLE_ROOM, null, values);
        db.close(); // Closing database connection
    }

    public Room getRoom(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TheaterContracts.Rooms.TABLE_ROOM, new String[]{TheaterContracts.Rooms.ID_ROOM,
                        TheaterContracts.Rooms. ROOM_NAME, TheaterContracts.Rooms.ROOM_DESC, TheaterContracts.Rooms.ROOM_SEATS}, TheaterContracts.Rooms.ID_ROOM + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Room room = new Room(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)));
        // return room
        return room;
    }

    //Methods for Show
    public void AddShow(Show show) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TheaterContracts.Shows.SHOW_MOVIE, show.getIdMovie());
        values.put(TheaterContracts.Shows.SHOW_ROOM, show.getIdRoom());
        values.put(TheaterContracts.Shows.SHOW_PRICE, show.getPrice());
        values.put(TheaterContracts.Shows.SHOW_REMAIN_SEAT, show.getRemainingSeat());
        values.put(TheaterContracts.Shows.SHOW_DATE, show.getDate());

        // Inserting Row
        db.insert(TheaterContracts.Shows.TABLE_SHOW, null, values);
        db.close(); // Closing database connection
    }

    public Show getShow(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TheaterContracts.Shows.TABLE_SHOW, new String[]{TheaterContracts.Shows.ID_SHOW,
                        TheaterContracts.Shows.SHOW_ROOM, TheaterContracts.Shows.SHOW_MOVIE, TheaterContracts.Shows.SHOW_PRICE,
                        TheaterContracts.Shows.SHOW_REMAIN_SEAT, TheaterContracts.Shows.SHOW_DATE}, TheaterContracts.Shows.ID_SHOW + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Show show = new Show(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)),
                Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(1)), cursor.getString(5));
        // return show
        return show;
    }

    public List<Show> getShowsByMovie(int id) {
        List<Show> showList = new ArrayList<Show>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TheaterContracts.Shows.TABLE_SHOW +" WHERE "+
                TheaterContracts.Shows.SHOW_MOVIE+" = "+id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                Show show= new Show();
                show.setIdShow(Integer.parseInt(cursor.getString(0)));
                show.setIdRoom(Integer.parseInt(cursor.getString(1)));
                show.setIdMovie(Integer.parseInt(cursor.getString(2)));
                show.setPrice(Integer.parseInt(cursor.getString(3)));
                show.setRemainingSeat(Integer.parseInt(cursor.getString(4)));
                show.setDate(cursor.getString(5));
                // Adding contact to list
                showList.add(show);
            } while (cursor.moveToNext());
        }

        // return movie list
        return showList;

    }

    //Methods for Booking
    public void AddBooking(Booking booking) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(BOOKING_CLIENT, booking.getClientName());
        values.put(BOOKING_NOSEATS, booking.getBookedSeats());
        values.put(BOOKING_SHOW, booking.getIdShow());


        // Inserting Row
        db.insert(TheaterContracts.Bookings.TABLE_BOOKING, null, values);
        db.close(); // Closing database connection
    }

    public Booking getBooking(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TheaterContracts.Bookings.TABLE_BOOKING, new String[]{ID_BOOKING,
                        BOOKING_SHOW, BOOKING_CLIENT, BOOKING_NOSEATS}, ID_BOOKING + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Booking booking = new Booking(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), cursor.getString(2), Integer.parseInt(cursor.getString(3)));
        // return show
        return booking;
    }

}
