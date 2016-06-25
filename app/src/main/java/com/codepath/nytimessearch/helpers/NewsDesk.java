package com.codepath.nytimessearch.helpers;

/**
 * Created by mbakhiet on 6/23/16.
 */
public class NewsDesk {
    private boolean arts;
    private boolean food;
    private boolean health;
    private boolean sports;
    private boolean politics;

    public void setArts(boolean arts) {
        this.arts = arts;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public void setHealth(boolean health) {
        this.health = health;
    }

    public void setSports(boolean sports) {
        this.sports = sports;
    }

    public void setPolitics(boolean politics) {
        this.politics = politics;
    }

    public NewsDesk(){
        arts = false;
        food = false;
        health = false;
        sports = false;
        politics = false;
    }
}
