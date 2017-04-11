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
    private static final String TABLE_ACTORS = "table_actors";
    private static final String TABLE_MOVIES = "table_movies";
    private static final String TABLE_ACTOR_MOVIE = "table_movie_actor";
    private static final String TABLE_ROOM = "table_room";
    private static final String TABLE_SHOW = "table_show";
    private static final String TABLE_BOOKING = "table_booking";


    //table Actor
    private static final String ID_ACTOR = "IdActor";
    private static final String ACTORNAME = "ActorName";

    //Table movie
    private static final String ID_MOVIE = "IdMovie";
    private static final String MOVIE_DESC = "Description";
    private static final String MOVIE_DUR = "Duration";

    //Table Actor Movie
    private static final String ID_ACTOR_MOVIE = "IdMovieActor";
    private static final String FILM_ACTOR = "MovieActor";
    private static final String FILM_MOVIE = "MovieFilm";

    //Table room
    private static final String ID_ROOM = "IdRoom";
    private static final String ROOM_NAME = "Name";
    private static final String ROOM_DESC = "Description";
    private static final String ROOM_SEATS = "NumberSeats";

    //Table Show
    private static final String ID_SHOW = "IdShow";
    private static final String SHOW_ROOM = "IdRoom";
    private static final String SHOW_MOVIE = "IdMovie";
    private static final String SHOW_PRICE = "Price";
    private static final String SHOW_REMAIN_SEAT = "RemainingSeats";
    private static final String SHOW_DATE = "Date";


    //Table Booking

    private static final String ID_BOOKING = "IdBooking";
    private static final String BOOKING_SHOW = "IdShow";
    private static final String BOOKING_CLIENT = "ClientName";
    private static final String BOOKING_NOSEATS = "BookedSeats";


    //Table Scripts
    private static final String CREATE_ACTOR_BDD = "CREATE TABLE " + TABLE_ACTORS + " (" +
            ID_ACTOR + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ACTORNAME + " TEXT NOT NULL);";
    private static final String CREATE_MOVIE_BDD = "CREATE TABLE " + TABLE_MOVIES + " (" +
            ID_MOVIE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MOVIE_DESC + " TEXT NOT NULL," + MOVIE_DUR + " TEXT NOT NULL);";

    private static final String CREATE_ACTOR_MOVIE_BDD =
            "CREATE TABLE " + TABLE_ACTOR_MOVIE +
                    " (" + ID_ACTOR_MOVIE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "" + FILM_ACTOR + " INTEGER NOT NULL, " +
                    " " + FILM_MOVIE + " INTEGER NOT NULL, " +
                    "FOREIGN KEY(" + FILM_ACTOR + ") REFERENCES " + TABLE_ACTORS + "(" + ID_ACTOR + "), " +
                    "FOREIGN KEY(" + FILM_MOVIE + ") REFERENCES " + TABLE_MOVIES + "(" + ID_MOVIE + "));";

    private static final String CREATE_ROOM_BDD = "CREATE TABLE " + TABLE_ROOM + " (" +
            ID_ROOM + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ROOM_NAME + " TEXT NOT NULL, " + ROOM_DESC + " TEXT NOT NULL, " + ROOM_SEATS +
            " INTEGER NOT NULL" + ");";

    private static final String CREATE_SHOW_BDD = "CREATE TABLE " + TABLE_SHOW + " (" +
            ID_SHOW + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SHOW_ROOM + " INTEGER, " +
            SHOW_MOVIE + " INTEGER, " + SHOW_PRICE + " DECIMAL NOT NULL, " +
            SHOW_REMAIN_SEAT + " INTEGER NOT NULL, " + SHOW_DATE + " TEXT NOT NULL, " +

            "FOREIGN KEY(" + SHOW_ROOM + ") REFERENCES " + TABLE_ROOM + "(" + ID_ROOM + "), " +
            "FOREIGN KEY(" + SHOW_MOVIE + ") REFERENCES " + TABLE_MOVIES + "(" + ID_MOVIE + "));";

    private static final String CREATE_BOOKING_BDD = "CREATE TABLE " + TABLE_BOOKING + " (" +
            ID_BOOKING + " INTEGER PRIMARY KEY AUTOINCREMENT, " + BOOKING_SHOW + " INTEGER, " +
            BOOKING_CLIENT + " TEXT NOT NULL, " + BOOKING_NOSEATS + " INTEGER NOT NULL, " +
            "FOREIGN KEY(" + BOOKING_SHOW + ") REFERENCES " + TABLE_SHOW + "(" + ID_SHOW + "));";

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
        db.execSQL("DROP TABLE " + TABLE_ACTORS + ", " + TABLE_MOVIES + ", " + TABLE_ACTOR_MOVIE + ", " +
                TABLE_SHOW + ", " + TABLE_BOOKING + ", " + TABLE_ROOM + ";");
        onCreate(db);
    }
    
    //All METHODS FOR ACTORS

    public void AddActor(Actor actor) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ACTORNAME, actor.getActorName());

        // Inserting Row
        db.insert(TABLE_ACTORS, null, values);
        db.close(); // Closing database connection
    }

    public Actor getActor(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ACTORS, new String[]{ID_ACTOR,
                        ACTORNAME}, ID_ACTOR + "=?",
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
        String selectQuery = "SELECT  * FROM " + TABLE_ACTORS;

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
        values.put(ACTORNAME, actor.getActorName());


        // updating row
        return db.update(TABLE_ACTORS, values, ID_ACTOR + " = ?",
                new String[]{String.valueOf(actor.getIdActor())});
    }


    public void deleteActor(Actor actor) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ACTORS, ID_ACTOR + " = ?",
                new String[]{String.valueOf(actor.getIdActor())});
        db.close();
    }

    //ALL METHODS FOR MOVIE

    public void AddMovie(Movie movie) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MOVIE_DESC, movie.getDescription());
        values.put(MOVIE_DUR, movie.getDuration());

        // Inserting Row
        db.insert(TABLE_MOVIES, null, values);
        db.close(); // Closing database connection
    }

    public Movie getMovie(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MOVIES, new String[]{ID_MOVIE,
                        MOVIE_DESC, MOVIE_DUR}, ID_MOVIE + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Movie movie = new Movie(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return movie
        return movie;
    }

    public List<Movie> getAllMovies() {
        List<Movie> movieList = new ArrayList<Movie>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MOVIES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                Movie movie = new Movie();
                movie.setIdMovie(Integer.parseInt(cursor.getString(0)));
                movie.setDescription(cursor.getString(1));
                movie.setDuration(cursor.getString(2));
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

        values.put(FILM_ACTOR, actor.getIdActor());
        values.put(FILM_MOVIE, movie.getIdMovie());

        // Inserting Row
        db.insert(TABLE_ACTOR_MOVIE, null, values);

        db.close(); // Closing database connection
    }

    public ArrayList<Actor> getAllActorInMovie(int ident) {
        ArrayList<Actor> actorList = new ArrayList<Actor>();
        // Select All Query
        String selectQuery = "SELECT " + FILM_ACTOR + " FROM " + TABLE_ACTOR_MOVIE +
                " WHERE " + FILM_MOVIE + "=" + ident + "; ";

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

        values.put(ROOM_DESC, room.getDescription());
        values.put(ROOM_NAME, room.getName());
        values.put(ROOM_SEATS, room.getNumberSeats());

        // Inserting Row
        db.insert(TABLE_ROOM, null, values);
        db.close(); // Closing database connection
    }

    public Room getRoom(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ROOM, new String[]{ID_ROOM,
                        ROOM_DESC, ROOM_NAME, ROOM_SEATS}, ID_ROOM + "=?",
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

        values.put(SHOW_MOVIE, show.getIdMovie());
        values.put(SHOW_ROOM, show.getIdRoom());
        values.put(SHOW_PRICE, show.getPrice());
        values.put(SHOW_REMAIN_SEAT, show.getRemainingSeat());
        values.put(SHOW_DATE, show.getDate());

        // Inserting Row
        db.insert(TABLE_SHOW, null, values);
        db.close(); // Closing database connection
    }

    public Show getShow(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SHOW, new String[]{ID_SHOW,
                        SHOW_ROOM, SHOW_MOVIE, SHOW_PRICE, SHOW_REMAIN_SEAT, SHOW_DATE}, ID_SHOW + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Show show = new Show(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)),
                Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(1)), cursor.getString(5));
        // return show
        return show;
    }

    //Methods for Booking
    public void AddBooking(Booking booking) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(BOOKING_CLIENT, booking.getClientName());
        values.put(BOOKING_NOSEATS, booking.getBookedSeats());
        values.put(BOOKING_SHOW, booking.getIdShow());


        // Inserting Row
        db.insert(TABLE_BOOKING, null, values);
        db.close(); // Closing database connection
    }

    public Booking getBooking(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_BOOKING, new String[]{ID_BOOKING,
                        BOOKING_SHOW, BOOKING_CLIENT, BOOKING_NOSEATS}, ID_BOOKING + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Booking booking = new Booking(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), cursor.getString(2), Integer.parseInt(cursor.getString(3)));
        // return show
        return booking;
    }

}
