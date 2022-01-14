package com.nikosmovie.review.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Response<T> implements Serializable {
    @SerializedName("page")
    String page;
    @SerializedName("results")
    ArrayList<T> results;
    @SerializedName("genres")
    ArrayList<T> genres;



    public ArrayList<T> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<T> genres) {
        this.genres = genres;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public ArrayList<T> getResults() {
        return results;
    }

    public void setResults(ArrayList<T> results) {
        this.results = results;
    }
}
