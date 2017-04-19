package com.example.lucien.theaterbooking.DatabaseObject;

/**
 * Created by Robert on 04.04.2017.
 */

public class Movie {

    private int idMovie;
    private String title;
    private String description;
    private String duration;

    public Movie() {
    }

    public Movie(String title, String duration) {
        this.title = title;
        this.duration = duration;
    }

    public Movie(String title, String description, String duration) {
        this.title = title;
        this.description = description;
        this.duration = duration;
    }

    public Movie(int idMovie, String title, String description, String duration) {
        this.idMovie = idMovie;
        this.title = title;
        this.description = description;
        this.duration = duration;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


}
