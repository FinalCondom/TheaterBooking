package com.example.lucien.theaterbooking.DatabaseTable;

import android.provider.BaseColumns;

/**
 * Created by Robert on 19.04.2017.
 */

public final class TheaterContracts {
    public TheaterContracts() {}

    public static abstract class Actors implements BaseColumns{

        //-----------Table name-------------
        public static final String TABLE_ACTORS = "table_actors";

        //-----------Table Column-----------
        public static final String ID_ACTOR = "IdActor";
        public static final String ACTORNAME = "ActorName";

    }
    public static abstract class  Movies implements BaseColumns{
        public Movies() {}

        //-----------Table name----------
        public static final String TABLE_MOVIES = "table_movies";

        //-----------Table Column----------
        public static final String ID_MOVIE = "IdMovie";
        public static final String MOVIE_TITLE="Title";
        public static final String MOVIE_DESC = "Description";
        public static final String MOVIE_DUR = "Duration";

    }

    public static abstract class FilmActor implements BaseColumns{
        public FilmActor() {}

        //-----------Table name---------
        public static final String TABLE_ACTOR_MOVIE = "table_movie_actor";

        //----------Table Column--------
        public static final String ID_ACTOR_MOVIE = "IdMovieActor";
        public static final String FILM_ACTOR = "MovieActor";
        public static final String FILM_MOVIE = "MovieFilm";


    }
    public static abstract class Rooms implements BaseColumns{
        public Rooms() {}

        //---------Table Name----------
        public static final String TABLE_ROOM = "table_room";

        //---------Table column--------
        public static final String ID_ROOM = "IdRoom";
        public static final String ROOM_NAME = "Name";
        public static final String ROOM_DESC = "Description";
        public static final String ROOM_SEATS = "NumberSeats";

    }
    public static abstract class Shows implements BaseColumns{
        public Shows() {}
        //----------Table Name----------
        public static final String TABLE_SHOW = "table_show";
        //----------Table Column--------
        public static final String ID_SHOW = "IdShow";
        public static final String SHOW_ROOM = "IdRoom";
        public static final String SHOW_MOVIE = "IdMovie";
        public static final String SHOW_PRICE = "Price";
        public static final String SHOW_REMAIN_SEAT = "RemainingSeats";
        public static final String SHOW_DATE = "Date";



    }
    public static abstract class Bookings implements BaseColumns{
        public Bookings() {}
        //--------Table Name--------
        public static final String TABLE_BOOKING = "table_booking";

        //-------Table Column-------
        public static final String ID_BOOKING = "IdBooking";
        public static final String BOOKING_SHOW = "IdShow";
        public static final String BOOKING_CLIENT = "ClientName";
        public static final String BOOKING_NOSEATS = "BookedSeats";

    }

}
