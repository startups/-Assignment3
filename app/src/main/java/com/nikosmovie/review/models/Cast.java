package com.nikosmovie.review.models;

import com.google.gson.annotations.SerializedName;

public class Cast {
    @SerializedName("name")
    String name;
    @SerializedName("character")
    String character;
    @SerializedName("profile_path")
    String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
