package com.nikosmovie.review.services;

import com.nikosmovie.review.models.Genre;
import com.nikosmovie.review.models.MovieDetails;
import com.nikosmovie.review.models.Response;
import com.nikosmovie.review.models.Results;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitApi {

    @GET("discover/movie")
    Observable<Response<Results>> getAllList(@Query("api_key") String apiKey, @Query("page") String page, @Query("sort_by") String sortBy);

    @GET("genre/movie/list")
    Observable<Response<Genre>> getAllGenre(@Query("api_key") String apiKey);

    @GET("discover/movie")
    Observable<Response<Results>> getCategoryMovieList(@Query("api_key") String apiKey,@Query("with_genres") String genreId);

    @GET("movie/{movie_id}")
    Observable<MovieDetails> getMovieDetails(@Path("movie_id") String movieId,@Query("api_key") String apiKey);

    @GET("movie/{movie_id}/credits")
    Observable<Results> getCast(@Path("movie_id") String movieId,@Query("api_key") String apiKey);

    @GET("search/movie")
    Observable<Response<Results>> findMovies(@Query("query")String query,@Query("api_key") String apiKey);

}
