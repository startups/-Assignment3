package com.nikosmovie.review.content.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nikosmovie.review.base.ViewBindingAdapter;
import com.nikosmovie.review.databinding.GenreItemBinding;
import com.nikosmovie.review.models.Genre;

public class GenreAdapter extends ViewBindingAdapter<Genre, GenreItemBinding> {

    @Override
    public GenreItemBinding bindingInflater(LayoutInflater layoutInflater, ViewGroup parent, Boolean attachParent) {
        return GenreItemBinding.inflate(layoutInflater,parent,attachParent);
    }

    @Override
    public void onItemViewReady(GenreItemBinding binding, Genre item, int position) {
            binding.btnGenre.setText(item.getName());

    }
}
