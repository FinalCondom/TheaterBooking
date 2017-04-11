package com.example.lucien.theaterbooking.DatabaseObject;

/**
 * Created by Robert on 04.04.2017.
 */

public class Actor {
    private int idActor;
    private String actorName;

    public Actor() {
    }
    public Actor(String actorName) {

        this.actorName = actorName;
    }

    public Actor(int idActor,String actorName) {
        this.idActor = idActor;
        this.actorName = actorName;
    }

    public int getIdActor() {
        return idActor;
    }

    public void setIdActor(int idActor) {
        this.idActor = idActor;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }
}
