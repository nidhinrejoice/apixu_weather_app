package com.nidhin.apixu_weather.home;


import android.app.MediaRouteButton;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.nidhin.apixu_weather.R;
import com.nidhin.apixu_weather.base.BaseActivity;
import com.nidhin.apixu_weather.data.model.Current;
import com.nidhin.apixu_weather.data.model.Forecastday;
import com.nidhin.apixu_weather.data.model.Location;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindAnim;
import butterknife.BindView;

public class HomeActivity extends BaseActivity {

    private HomeViewModel viewModel;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.currentTemp)
    TextView tvCurrentTemp;
    @BindView(R.id.condition)
    TextView tvCondition;
    @BindView(R.id.location)
    TextView tvLocation;
    @BindView(R.id.icon)
    ImageView ivIcon;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel.class);
        viewModel.isLoading().observe(this, this::setProgress);
        viewModel.getErrorMsg().observe(this, this::showError);
        viewModel.getCurrentWeather().observe(this, this::setCurrentWeather);
        viewModel.getForecastData().observe(this, this::setForeCastData);
        viewModel.getCurrentLocation().observe(this, this::setCurrentLocation);
        swipeRefreshLayout.setOnRefreshListener(() -> viewModel.refresh());
        viewModel.onScreenCreated();
    }

    private void setCurrentLocation(Location location) {
        if (location != null) {
            tvLocation.setText(location.getName());
        }
    }

    private void setCurrentWeather(Current current) {
        if (current != null) {
            tvCurrentTemp.setText(String.valueOf(current.getTempC()) + "°");
            Glide.with(this).load("https:" + current.getCondition().getIcon())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(ivIcon);
            tvCondition.setText("(" + current.getCondition().getText() + ")");
        }
    }

    private void setForeCastData(List<Forecastday> list) {
        if (list != null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            ForeCastAdapter adapter = new ForeCastAdapter(this, list);
            recyclerView.setLayoutManager(layoutManager);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                    layoutManager.getOrientation());
            recyclerView.addItemDecoration(dividerItemDecoration);
            recyclerView.setAdapter(adapter);
        }
    }

    private void setProgress(boolean isLoading) {
        if (isLoading) {
            showProgress();
        } else {
            hideProgress();
        }
    }

    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void showProgress() {
        swipeRefreshLayout.setRefreshing(true);
        progress.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
        progress.setVisibility(View.GONE);
    }

}
