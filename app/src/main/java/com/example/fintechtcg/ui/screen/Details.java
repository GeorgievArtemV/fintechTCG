package com.example.fintechtcg.ui.screen;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.MyApp;
import com.example.fintechtcg.R;
import com.example.fintechtcg.databinding.FragmentCoreBinding;
import com.example.fintechtcg.databinding.FragmentDetailsBinding;
import com.example.fintechtcg.model.FilmDetail;
import com.example.fintechtcg.model.ListFilms;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerDrawable;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Details extends Fragment {

    private DetailsViewModel mViewModel;

    FragmentDetailsBinding binding;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            int proba = bundle.getInt("1");

            Call<FilmDetail> call = MyApp.filmAPI.getFilmDetails(proba);
            call.enqueue(new Callback<FilmDetail>() {
                @Override
                public void onResponse(Call<FilmDetail> call, Response<FilmDetail> response) {

                    Glide.with(binding.imageDetails).load(response.body().getPosterUrl()).placeholder(getShimmerDrawable()).diskCacheStrategy(DiskCacheStrategy.ALL).into(binding.imageDetails);
                    binding.nameDetails.setText(response.body().getNameRu());
                    binding.descriptionDetails.setText(response.body().getDescription());

                }

                @Override
                public void onFailure(Call<FilmDetail> call, Throwable t) {
                    Log.e("TAG", t.getMessage());

                    //call.clone().enqueue(this);
                }
            });

        }
        return view;

    }
    public ShimmerDrawable getShimmerDrawable() {
        Shimmer shimmer = new Shimmer.AlphaHighlightBuilder()
                .setDuration(1800)
                .setBaseAlpha(0.7f)
                .setHighlightAlpha(0.6f)
                .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                .setAutoStart(true)
                .build();
        ShimmerDrawable shimmerDrawable = new ShimmerDrawable();
        shimmerDrawable.setShimmer(shimmer);

        return shimmerDrawable;
    }

}