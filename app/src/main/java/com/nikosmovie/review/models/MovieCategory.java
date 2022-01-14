package com.nikosmovie.review.models;

import java.io.Serializable;
import java.util.ArrayList;

public class MovieCategory implements Serializable {
    String title;
    String id;
    ArrayList<Results> moviesList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Results> getMoviesList() {
        return moviesList;
    }

    public void setMoviesList(ArrayList<Results> moviesList) {
        this.moviesList = moviesList;
    }
}
