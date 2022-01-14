package com.nikosmovie.review.content.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nikosmovie.review.base.ViewBindingAdapter;
import com.nikosmovie.review.databinding.MovieCategoryItemBinding;
import com.nikosmovie.review.models.MovieCategory;
import com.nikosmovie.review.models.Results;
import com.nikosmovie.review.util.CustomCallback;

public class MovieCategoryAdapter extends ViewBindingAdapter<MovieCategory, MovieCategoryItemBinding> {

    public CustomCallback<String> moovieClick;

    @Override
    public MovieCategoryItemBinding bindingInflater(LayoutInflater layoutInflater, ViewGroup parent, Boolean attachParent) {
        return MovieCategoryItemBinding.inflate(layoutInflater,parent,attachParent);
    }

    @Override
    public void onItemViewReady(MovieCategoryItemBinding binding, MovieCategory item, int position) {
        MoviesAdapter adapter = new MoviesAdapter();
        binding.txtCategoryTitle.setText(item.getTitle());
        binding.rvCategoryMovie.setAdapter(adapter);
        adapter.list = list.get(position).getMoviesList();
        adapter.notifyDataSetChanged();

        adapter.onItemClick = results -> {
            if(moovieClick != null){
                moovieClick.callback(results.getId());
            }
        };

    }
}
