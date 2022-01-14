package com.nikosmovie.review.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {
    static Retrofit newInstance;

    private Retrofit getInstance(){
        if(newInstance == null){
            Gson gson = new GsonBuilder().setLenient().create();

            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(2,TimeUnit.MINUTES)
                    .connectTimeout(2,TimeUnit.MINUTES)
                    .build();

            newInstance = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return newInstance;
    }

    public RetrofitApi createService(){
        return getInstance().create(RetrofitApi.class);
    }

}
