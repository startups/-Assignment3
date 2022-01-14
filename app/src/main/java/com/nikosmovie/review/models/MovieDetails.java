package com.nikosmovie.review.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Locale;

public class MovieDetails {
    @SerializedName("backdrop_path")
    String image;
    @SerializedName("genres")
    ArrayList<Genre> genreList;
    @SerializedName("original_language")
    String language;
    @SerializedName("original_title")
    String title;
    @SerializedName("overview")
    String description;
    @SerializedName("release_date")
    String date;
    @SerializedName("runtime")
    String time;
    @SerializedName("vote_average")
    Float rating;
    @SerializedName("vote_count")
    String votes;
    String genreGenerated;

    public String getGenreGenerated() {
        genreGenerated = "";
        for(Genre genre : this.genreList){
            genreGenerated += genre.getName()+"/";
        }
        genreGenerated = genreGenerated.substring(0,genreGenerated.length()-1);
        return genreGenerated;
    }

    public void setGenreGenerated(String genreGenerated) {
        this.genreGenerated = genreGenerated;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(ArrayList<Genre> genreList) {
        this.genreList = genreList;
    }

    public String getLanguage() {
        Locale loc = new Locale(language);
        language = loc.getDisplayLanguage(loc);
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        Double newTime = Double.parseDouble(time);
        int hour = (int) (newTime/60);
        int minutes = (int) (newTime - hour*60);
        String minutesString = (minutes <= 9 ) ? "0" + minutes : ""+minutes;
        return hour+":"+minutesString;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Float getRating() {
        return rating/2;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getVotes() {
        return votes +" votes";
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }
}
