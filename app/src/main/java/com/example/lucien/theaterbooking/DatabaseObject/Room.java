package com.example.lucien.theaterbooking.DatabaseObject;

/**
 * Created by Robert on 06.04.2017.
 */

public class Room {

    private int idRoom;
    private String name;
    private String description;
    private int numberSeats;

    public Room() {
    }

    public Room(String name, String description, int numberSeats) {
        this.name = name;
        this.description = description;
        this.numberSeats = numberSeats;
    }

    public Room(int idRoom, String name, String description, int numberSeats) {
        this.idRoom = idRoom;
        this.name = name;
        this.description = description;
        this.numberSeats = numberSeats;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberSeats() {
        return numberSeats;
    }

    public void setNumberSeats(int numberSeats) {
        this.numberSeats = numberSeats;
    }
}
