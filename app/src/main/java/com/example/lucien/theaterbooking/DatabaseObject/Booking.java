package com.example.lucien.theaterbooking.DatabaseObject;

/**
 * Created by Robert on 06.04.2017.
 */

public class Booking {
    private int idBooking;
    private int idShow;
    private String clientName;
    private int bookedSeats;

    public Booking() {
    }

    public Booking(int idShow, String clientName, int bookedSeats) {
        this.idShow = idShow;
        this.clientName = clientName;
        this.bookedSeats = bookedSeats;
    }

    public Booking(int idBooking, int idShow, String clientName, int bookedSeats) {
        this.idBooking = idBooking;
        this.idShow = idShow;
        this.clientName = clientName;
        this.bookedSeats = bookedSeats;
    }

    public int getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(int idBooking) {
        this.idBooking = idBooking;
    }

    public int getIdShow() {
        return idShow;
    }

    public void setIdShow(int idShow) {
        this.idShow = idShow;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(int bookedSeats) {
        this.bookedSeats = bookedSeats;
    }
}
