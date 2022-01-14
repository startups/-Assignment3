package com.nikosmovie.review.content.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nikosmovie.review.base.ViewBindingAdapter;
import com.nikosmovie.review.databinding.SearchItemBinding;
import com.nikosmovie.review.models.Results;
import com.nikosmovie.review.util.Constants;
import com.nikosmovie.review.util.CustomCallback;
import com.nikosmovie.review.util.Extensions;

public class SearchAdapter extends ViewBindingAdapter<Results, SearchItemBinding> {



    @Override
    public SearchItemBinding bindingInflater(LayoutInflater layoutInflater, ViewGroup parent, Boolean attachParent) {
        return SearchItemBinding.inflate(layoutInflater,parent,attachParent);
    }

    @Override
    public void onItemViewReady(SearchItemBinding binding, Results item, int position) {
        Extensions.showImage(binding.imgMovie, Constants.getImageBasePath()+item.getImage());
        binding.txtMovieTitle.setText(item.getTitle());

    }
}
