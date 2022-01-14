package com.nikosmovie.review.content.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nikosmovie.review.base.ViewBindingAdapter;
import com.nikosmovie.review.databinding.CategoryItemBinding;
import com.nikosmovie.review.models.Results;
import com.nikosmovie.review.util.Constants;
import com.nikosmovie.review.util.Extensions;

public class MoviesAdapter extends ViewBindingAdapter<Results, CategoryItemBinding> {
    @Override
    public CategoryItemBinding bindingInflater(LayoutInflater layoutInflater, ViewGroup parent, Boolean attachParent) {
        return CategoryItemBinding.inflate(layoutInflater,parent,attachParent);
    }

    @Override
    public void onItemViewReady(CategoryItemBinding binding, Results item, int position) {
        Extensions.showImage(binding.imgMovie, Constants.getImageBasePath() + item.getImage());
        binding.txtMovieTitle.setText(item.getTitle());
    }
}
