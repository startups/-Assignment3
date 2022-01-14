package com.nikosmovie.review.content.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nikosmovie.review.base.ViewModelBase;
import com.nikosmovie.review.models.Genre;
import com.nikosmovie.review.models.MovieCategory;
import com.nikosmovie.review.models.Results;
import com.nikosmovie.review.util.Constants;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;

public class HomeVM extends ViewModelBase {

    private MutableLiveData<ArrayList<Results>> _getDataSuccess = new MutableLiveData<>();
    LiveData<ArrayList<Results>> getDataSuccess(){
        return _getDataSuccess;
    }

    private MutableLiveData<ArrayList<Genre>> _getGenreSuccess = new MutableLiveData<>();
    LiveData<ArrayList<Genre>> getGenreSuccess() { return _getGenreSuccess;}

    private MutableLiveData<ArrayList<CarouselItem>> _getCarouselItemSuccess = new MutableLiveData<>();
    LiveData<ArrayList<CarouselItem>> getCarouselItemSuccess(){ return _getCarouselItemSuccess; }

    private MutableLiveData<ArrayList<MovieCategory>> _getMovieCategoryListSuccess = new MutableLiveData<>();
    LiveData<ArrayList<MovieCategory>> getMovieCategoryListSuccess(){ return _getMovieCategoryListSuccess;}


    public void getDiscover(String page, ArrayList<Genre> genreList){
        ArrayList<CarouselItem> carouselItems = new ArrayList<>();
        connection.connection(api.getAllList(Constants.getApiKey(),page,"popularity.desc"), response -> {

            for(Results results : response.getResults()){
                carouselItems.add(new CarouselItem(Constants.getImageBasePath() + results.getImage(),results.getId()));
                results.setGenres(findGenres(genreList,results.getGenreIds()));
            }
            _getDataSuccess.setValue(response.getResults());
            _getCarouselItemSuccess.setValue(carouselItems);
        }, error -> {
            Log.d("MainVM","error " + error);
        });
    }


    public void getGenres(){
        connection.connection(api.getAllGenre(Constants.getApiKey()),response -> {
            getGenreMovies(response.getGenres());
           _getGenreSuccess.setValue(response.getGenres());
        },error -> {
            Log.d("MainVM","Error " + error);
        });
    }


    private void getGenreMovies(ArrayList<Genre> genreList){
        ArrayList<MovieCategory> movieCategoryList = new ArrayList<>();
        for(Genre genre : genreList){
            connection.connection(api.getCategoryMovieList(Constants.getApiKey(),genre.getId()),res -> {
                MovieCategory movieCategory = new MovieCategory();
                movieCategory.setId(genre.getId());
                movieCategory.setTitle(genre.getName());
                movieCategory.setMoviesList(res.getResults());
                movieCategoryList.add(movieCategory);
                if(movieCategoryList.size() == genreList.size()){
                    _getMovieCategoryListSuccess.setValue(movieCategoryList);
                }
            },error -> {
                Log.d("MainVM","Error " + error);
            });
        }


    }


    private ArrayList<Genre> findGenres(ArrayList<Genre> genreList,ArrayList<String> discoverGenre){
        ArrayList<Genre> genreListGenerated = new ArrayList<>();
        for (Genre genre : genreList){
            for(String genreId : discoverGenre){
                if(genre.getId().equalsIgnoreCase(genreId)){
                    genreListGenerated.add(genre);
                }
            }
        }
        return genreListGenerated;
    }

}
