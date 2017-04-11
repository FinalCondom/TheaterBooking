package com.example.lucien.theaterbooking.DatabaseObject;

/**
 * Created by Robert on 06.04.2017.
 */

public class Show {

    private int idShow;
    private int idRoom;
    private int idMovie;
    private float price;
    private int remainingSeat;
    private String date;

    public Show() {
    }

    public Show(int idRoom, int idMovie, float price, int remainingSeat, String date) {
        this.idRoom = idRoom;
        this.idMovie = idMovie;
        this.price = price;
        this.remainingSeat = remainingSeat;
        this.date = date;
    }

    public Show(int idShow, int idRoom, int idMovie, float price, int remainingSeat, String date) {
        this.idShow = idShow;
        this.idRoom = idRoom;
        this.idMovie = idMovie;
        this.price = price;
        this.remainingSeat = remainingSeat;
        this.date = date;
    }

    public int getIdShow() {
        return idShow;
    }

    public void setIdShow(int idShow) {
        this.idShow = idShow;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getRemainingSeat() {
        return remainingSeat;
    }

    public void setRemainingSeat(int remainingSeat) {
        this.remainingSeat = remainingSeat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
