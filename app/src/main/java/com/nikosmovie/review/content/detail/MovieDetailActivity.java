package com.nikosmovie.review.content.detail;

import android.view.LayoutInflater;

import com.nikosmovie.review.base.ViewBindingActivity;
import com.nikosmovie.review.content.adapters.CastAdapter;
import com.nikosmovie.review.databinding.ActivityMovieDetailBinding;
import com.nikosmovie.review.util.Constants;
import com.nikosmovie.review.util.Extensions;

public class MovieDetailActivity extends ViewBindingActivity<ActivityMovieDetailBinding, MovieDetailVM> {

    private CastAdapter castAdapter = new CastAdapter();

    @Override
    public ActivityMovieDetailBinding inflateLayout(LayoutInflater layoutInflater) {
        return ActivityMovieDetailBinding.inflate(layoutInflater);
    }

    @Override
    public void viewListeners() {
        customDialog.show();
        binding.btnBack.setOnClickListener(s -> {
            onBackPressed();
        });

        binding.rvCast.setAdapter(castAdapter);

        viewModelListener();

    }

    private void viewModelListener() {
        final int[] count = {0};
        String id = getIntent().getStringExtra("movieId");
        viewModel.getMovieDetails(id);
        viewModel.getCast(id);
        viewModel.getMovieDetailSuccess().observe(this, movieDetails -> {
            Extensions.showImage(binding.imgPort, Constants.getImageBasePath() + movieDetails.getImage());
            binding.txtMovieTitle.setText(movieDetails.getTitle());
            binding.txtTitle.setText(movieDetails.getTitle());
            binding.rbVotes.setRating(movieDetails.getRating());
            binding.txtVotes.setText(movieDetails.getVotes());
            binding.txtDate.setText(movieDetails.getDate());
            binding.txtDescription.setText(movieDetails.getDescription());
            binding.txtDuration.setText(movieDetails.getTime());
            binding.txtLanguage.setText(movieDetails.getLanguage());
            binding.txtGenre.setText(movieDetails.getGenreGenerated());
            count[0]++;
            checkComplete(count[0], 2);
        });

        viewModel.getCastSuccess().observe(this, castList -> {
            castAdapter.list = castList;
            castAdapter.notifyDataSetChanged();
            count[0]++;
            checkComplete(count[0], 2);
        });
    }
}