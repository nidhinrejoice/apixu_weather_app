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
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nidhin.apixu_weather.R;
import com.nidhin.apixu_weather.base.BaseActivity;
import com.nidhin.apixu_weather.data.model.Current;

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
    @BindView(R.id.location)
    TextView tvLocation;
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
        swipeRefreshLayout.setOnRefreshListener(() -> viewModel.refresh());
        viewModel.onScreenCreated();
    }

    private void setCurrentWeather(Current current) {
        if (current != null) {
            tvCurrentTemp.setText(String.valueOf(current.getTempC()) + "°C");
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
