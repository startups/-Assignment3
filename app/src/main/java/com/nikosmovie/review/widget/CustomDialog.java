package com.nikosmovie.review.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.Window;

import androidx.annotation.NonNull;

import com.nikosmovie.review.databinding.CustomDialogBinding;

public class CustomDialog extends Dialog {

    public CustomDialog(@NonNull Context context) {
        super(context);
        initDialog();
    }

    private void initDialog() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        CustomDialogBinding binding = CustomDialogBinding.inflate(LayoutInflater.from(getContext()));
        setContentView(binding.getRoot());
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }


}
