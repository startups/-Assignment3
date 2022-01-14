package com.nikosmovie.review.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.nikosmovie.review.util.CustomCallback;

import java.util.ArrayList;
import java.util.Objects;

public abstract class ViewBindingAdapter<T, VB extends ViewBinding> extends RecyclerView.Adapter<ViewBindingAdapter<T,VB>.ViewHolder> {

    public ArrayList<T> list = new ArrayList<>();

    public abstract VB bindingInflater(LayoutInflater layoutInflater, ViewGroup parent, Boolean attachParent);

    public abstract void onItemViewReady(VB binding, T item,int position);

    public CustomCallback<T> onItemClick;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(bindingInflater(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        onItemViewReady(holder.binding, list.get(position),position);
        holder.itemView.setOnClickListener(s -> {
            if(onItemClick != null){
                onItemClick.callback(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private VB binding;
        public ViewHolder(VB binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
