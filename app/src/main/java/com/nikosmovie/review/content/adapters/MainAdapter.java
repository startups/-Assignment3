package com.nikosmovie.review.content.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nikosmovie.review.base.ViewBindingAdapter;
import com.nikosmovie.review.databinding.MainItemBinding;
import com.nikosmovie.review.models.Results;
import com.nikosmovie.review.util.Constants;
import com.nikosmovie.review.util.Extensions;

public class MainAdapter extends ViewBindingAdapter<Results, MainItemBinding>  {

    @Override
    public MainItemBinding bindingInflater(LayoutInflater layoutInflater, ViewGroup parent, Boolean attachParent) {
        return MainItemBinding.inflate(layoutInflater,parent,attachParent);
    }

    @Override
    public void onItemViewReady(MainItemBinding binding, Results item, int position) {
        Extensions.showImage(binding.imgMovie, Constants.getImageBasePath()+item.getImage());
    }


}
