package com.nikosmovie.review.services;

import android.os.Handler;
import android.telecom.Call;

import com.nikosmovie.review.util.CustomCallback;

import java.util.function.Function;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RetrofitConnection {

    private Disposable disposable;

    public<T> void connection(Observable<T> method, CustomCallback<T> success, CustomCallback<String> error){
        disposable = method.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(success::callback, err -> {
            error.callback(err.getMessage());
        });
    }

}
