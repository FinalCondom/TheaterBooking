package com.example.lucien.theaterbooking;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.lucien.theaterbooking.DatabaseObject.Booking;
import com.example.lucien.theaterbooking.DatabaseObject.Room;
import com.example.lucien.theaterbooking.DatabaseObject.Show;
import com.example.lucien.theaterbooking.DatabaseTable.DatabaseHandler;

import java.util.ArrayList;

/**
 * Created by lucien on 04.04.2017.
 */

public class ScreeningActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screening);

        DatabaseHandler db=new DatabaseHandler(this);
        String title=getIntent().getStringExtra("movie");
        int id=getIntent().getIntExtra("showId",0);
        Show show=db.getShow(id);
        Room room=db.getRoom(show.getIdRoom());

        TextView movie=(TextView)findViewById(R.id.MovieName);
        TextView theater=(TextView)findViewById(R.id.TheaterName);
        TextView roomDesc=(TextView)findViewById(R.id.RoomDescName);
        TextView adress=(TextView)findViewById(R.id.AddressName);
        TextView date=(TextView)findViewById(R.id.DateName);
        TextView occupation=(TextView)findViewById(R.id.OccupationName);



        movie.setText(title);
        theater.setText(room.getName());
        roomDesc.setText(room.getDescription());
        adress.setText(getString(R.string.AddressName));
        date.setText(show.getDate());
        occupation.setText(String.valueOf(show.getRemainingSeat()));

    }


    public void startBooking(View v){
        CharSequence[] array = {"1", "2", "3", "4", "5"};
        new AlertDialog.Builder(this)
                .setTitle("How many tickets do you want to book ?")
                .setSingleChoiceItems( array, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                    }
                })
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .show();
    }

}
