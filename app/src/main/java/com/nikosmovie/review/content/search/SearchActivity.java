package com.nikosmovie.review.content.search;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.nikosmovie.review.base.ViewBindingActivity;
import com.nikosmovie.review.content.adapters.SearchAdapter;
import com.nikosmovie.review.content.detail.MovieDetailActivity;
import com.nikosmovie.review.databinding.ActivitySearchBinding;

public class SearchActivity extends ViewBindingActivity<ActivitySearchBinding,SearchVM> {

    private SearchAdapter adapter = new SearchAdapter();


    @Override
    public ActivitySearchBinding inflateLayout(LayoutInflater layoutInflater) {
        return ActivitySearchBinding.inflate(layoutInflater);
    }

    @Override
    public void viewListeners() {
        binding.rvSearch.setAdapter(adapter);

        binding.txtSearch.setOnClickListener(v -> {
            customDialog.show();
            viewModel.searchMovie(binding.etSearch.getText().toString());
        });

        binding.btnBack.setOnClickListener(v -> {
            onBackPressed();
        });

        adapter.onItemClick = movieId -> {
            Intent intent = new Intent(this, MovieDetailActivity.class);
            intent.putExtra("movieId",movieId.getId());
            startActivity(intent);
        };


        viewModel.searchSuccess().observe(this,results -> {
            customDialog.hide();
            binding.txtNoData.setVisibility( results.size() > 0 ? View.GONE : View.VISIBLE);
            adapter.list = results;
            adapter.notifyDataSetChanged();
        });
    }
}