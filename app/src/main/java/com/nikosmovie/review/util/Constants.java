package com.nikosmovie.review.util;

public class Constants {
    static String imageBasePath = "https://image.tmdb.org/t/p/original";
    static String basePath = "https://api.themoviedb.org/3/";
    static String apiKey = "f371657bdf5dfe850d741ab0b1657e58";

    public static String getImageBasePath() {
        return imageBasePath;
    }

    public static void setImageBasePath(String imageBasePath) {
        Constants.imageBasePath = imageBasePath;
    }

    public static String getBasePath() {
        return basePath;
    }

    public static void setBasePath(String basePath) {
        Constants.basePath = basePath;
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static void setApiKey(String apiKey) {
        Constants.apiKey = apiKey;
    }
}
