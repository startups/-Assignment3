package com.nikosmovie.review.content.detail;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nikosmovie.review.base.ViewModelBase;
import com.nikosmovie.review.models.Cast;
import com.nikosmovie.review.models.MovieDetails;
import com.nikosmovie.review.util.Constants;

import java.util.ArrayList;

public class MovieDetailVM extends ViewModelBase {

    private MutableLiveData<MovieDetails> _getMovieDetailSuccess = new MutableLiveData<>();

    LiveData<MovieDetails> getMovieDetailSuccess() {
        return _getMovieDetailSuccess;
    }

    private MutableLiveData<ArrayList<Cast>> _getCastSuccess = new MutableLiveData<>();

    LiveData<ArrayList<Cast>> getCastSuccess() {
        return _getCastSuccess;
    }

    public void getMovieDetails(String movieId) {
        connection.connection(api.getMovieDetails(movieId, Constants.getApiKey()), result -> {
            _getMovieDetailSuccess.setValue(result);
        }, error -> {
            Log.d("MovieDetails", "error " + error);
        });
    }

    public void getCast(String movieId) {
        connection.connection(api.getCast(movieId, Constants.getApiKey()), results -> {
            _getCastSuccess.setValue(results.getCastList());
        }, error -> {
            Log.d("MovieDetails", "error " + error);
        });
    }

}
