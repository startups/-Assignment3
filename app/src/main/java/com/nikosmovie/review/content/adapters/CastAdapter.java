package com.nikosmovie.review.content.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nikosmovie.review.base.ViewBindingAdapter;
import com.nikosmovie.review.databinding.CastItemBinding;
import com.nikosmovie.review.models.Cast;
import com.nikosmovie.review.util.Constants;
import com.nikosmovie.review.util.Extensions;

public class CastAdapter extends ViewBindingAdapter<Cast, CastItemBinding> {
    @Override
    public CastItemBinding bindingInflater(LayoutInflater layoutInflater, ViewGroup parent, Boolean attachParent) {
        return CastItemBinding.inflate(layoutInflater,parent,attachParent);
    }

    @Override
    public void onItemViewReady(CastItemBinding binding, Cast item, int position) {
        Extensions.showImage(binding.imgCast, Constants.getImageBasePath()+item.getImage());
        binding.txtNameCast.setText(item.getName());
        binding.txtCharacter.setText(item.getCharacter());
    }
}
