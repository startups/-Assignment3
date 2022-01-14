package com.nikosmovie.review.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Results implements Serializable {
    @SerializedName("backdrop_path")
    String image;
    String id;
    @SerializedName("original_title")
    String title;
    @SerializedName("genre_ids")
    ArrayList<String> genreIds;
    ArrayList<Genre> genres;
    @SerializedName("vote_count")
    String votes;
    @SerializedName("vote_average")
    Float rating;
    @SerializedName("cast")
    ArrayList<Cast> castList;

    public ArrayList<Cast> getCastList() {
        return castList;
    }

    public void setCastList(ArrayList<Cast> castList) {
        this.castList = castList;
    }

    public String getVotes() {
        return votes +" votes";
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }

    public Float getRating() {
        return rating/2;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    public ArrayList<String> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(ArrayList<String> genreIds) {
        this.genreIds = genreIds;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
