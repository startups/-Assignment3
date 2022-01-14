package com.nikosmovie.review.base;

import androidx.lifecycle.ViewModel;

import com.nikosmovie.review.services.RetrofitApi;
import com.nikosmovie.review.services.RetrofitClient;
import com.nikosmovie.review.services.RetrofitConnection;

public class ViewModelBase extends ViewModel {

    public RetrofitConnection connection = new RetrofitConnection();
    public RetrofitApi api = new RetrofitClient().createService();
}
