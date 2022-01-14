package com.nikosmovie.review.util;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nikosmovie.review.R;

public class Extensions {

    public static void showImage(ImageView imageView,String url){
        Glide.with(imageView.getContext()).load(url)
                .placeholder(R.drawable.no_image)
                .into(imageView);
    }

}



