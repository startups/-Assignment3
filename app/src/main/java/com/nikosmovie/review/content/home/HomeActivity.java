package com.nikosmovie.review.content.home;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.google.android.material.snackbar.Snackbar;
import com.nikosmovie.review.R;
import com.nikosmovie.review.base.ViewBindingActivity;
import com.nikosmovie.review.content.adapters.GenreAdapter;
import com.nikosmovie.review.content.adapters.MainAdapter;
import com.nikosmovie.review.content.adapters.MovieCategoryAdapter;
import com.nikosmovie.review.content.detail.MovieDetailActivity;
import com.nikosmovie.review.content.search.SearchActivity;
import com.nikosmovie.review.databinding.ActivityHomeBinding;
import com.nikosmovie.review.models.Genre;
import com.novoda.merlin.Bindable;
import com.novoda.merlin.Connectable;
import com.novoda.merlin.Disconnectable;
import com.novoda.merlin.NetworkStatus;

import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener;
import org.imaginativeworld.whynotimagecarousel.listener.CarouselOnScrollListener;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;

public class HomeActivity extends ViewBindingActivity<ActivityHomeBinding, HomeVM> {

    private MainAdapter adapter = new MainAdapter();
    private GenreAdapter genreAdapter = new GenreAdapter();
    private MovieCategoryAdapter movieCategoryAdapter = new MovieCategoryAdapter();
    private int actualItemPosition = -1;



    @Override
    public ActivityHomeBinding inflateLayout(LayoutInflater layoutInflater) {
        return ActivityHomeBinding.inflate(layoutInflater);
    }

    @Override
    public void viewListeners() {


        customDialog.show();
        viewModelListeners();
        binding.rvGenrer.setAdapter(genreAdapter);
        binding.imgCarousel.setAutoPlayDelay(5000);
        binding.imgCarousel.setAutoPlay(true);

        binding.imgSearch.setOnClickListener(s -> {
            startActivity(new Intent(HomeActivity.this, SearchActivity.class));
        });

        movieCategoryAdapter.moovieClick = movieId -> {
            Intent intent = new Intent(this, MovieDetailActivity.class);
            intent.putExtra("movieId",movieId);
            startActivity(intent);
        };

        binding.rvMovieCategory.setAdapter(movieCategoryAdapter);

        binding.imgCarousel.setCarouselListener(new CarouselListener() {
            @Override
            public ViewBinding onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
                return null;
            }

            @Override
            public void onBindViewHolder(ViewBinding viewBinding, CarouselItem carouselItem, int i) {

            }

            @Override
            public void onClick(int i, CarouselItem carouselItem) {
                Intent intent = new Intent(HomeActivity.this, MovieDetailActivity.class);
                intent.putExtra("movieId",adapter.list.get(binding.imgCarousel.getCurrentPosition()).getId());
                startActivity(intent);
            }

            @Override
            public void onLongClick(int i, CarouselItem carouselItem) {

            }
        });


        binding.imgCarousel.setOnScrollListener(new CarouselOnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int i, int i1, CarouselItem carouselItem) {
                System.out.println(i);
                if(i == 0 && actualItemPosition != i1){
                    actualItemPosition = i1;
                    ArrayList<Genre> genres = adapter.list.get(actualItemPosition).getGenres();
                    genreAdapter.list = genres;
                    genreAdapter.notifyDataSetChanged();
                    binding.txtMovieTitle.setText(adapter.list.get(actualItemPosition).getTitle());
                    binding.txtVotes.setText(adapter.list.get(actualItemPosition).getVotes());
                    binding.rbVotes.setRating(adapter.list.get(actualItemPosition).getRating());
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int i, int i1, int i2, CarouselItem carouselItem) {

            }
        });

    }


    private void viewModelListeners(){
        viewModel.getGenres();

        final int[] count = {0};

        viewModel.getDataSuccess().observe(this, s -> {
            adapter.list = s;
            adapter.notifyDataSetChanged();
            count[0]++;
            checkComplete(count[0],4);
        });

        viewModel.getGenreSuccess().observe(this, s->{
            viewModel.getDiscover("2",s);
            count[0]++;
            checkComplete(count[0],4);
        });

        viewModel.getCarouselItemSuccess().observe(this, items ->{
            binding.imgCarousel.setData(items);
            count[0]++;
            checkComplete(count[0],4);
        });

        viewModel.getMovieCategoryListSuccess().observe(this,res -> {
            movieCategoryAdapter.list = res;
            movieCategoryAdapter.notifyDataSetChanged();
            count[0]++;
            checkComplete(count[0],4);
        });
    }



}