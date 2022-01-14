package com.nikosmovie.review.content.search;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nikosmovie.review.base.ViewModelBase;
import com.nikosmovie.review.models.Results;
import com.nikosmovie.review.util.Constants;

import java.util.ArrayList;

public class SearchVM extends ViewModelBase {

    private MutableLiveData<ArrayList<Results>> _searchSuccess = new MutableLiveData<>();
    LiveData<ArrayList<Results>> searchSuccess()  {return _searchSuccess;}

    void searchMovie(String query){
        connection.connection(api.findMovies(query, Constants.getApiKey()),result -> {
            _searchSuccess.setValue(result.getResults());
        },error -> {
            Log.d("SearchVM","error " + error);
        });
    }


}
