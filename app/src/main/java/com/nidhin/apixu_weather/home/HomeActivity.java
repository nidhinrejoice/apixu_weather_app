package com.nidhin.apixu_weather.home;


import android.Manifest;
import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.nidhin.apixu_weather.R;
import com.nidhin.apixu_weather.base.BaseActivity;
import com.nidhin.apixu_weather.data.model.Current;
import com.nidhin.apixu_weather.data.model.Forecastday;
import com.nidhin.apixu_weather.data.model.Location;
import com.nidhin.apixu_weather.util.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class HomeActivity extends BaseActivity {

    private HomeViewModel viewModel;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @BindView(R.id.currentTemp)
    TextView tvCurrentTemp;
    @BindView(R.id.condition)
    TextView tvCondition;
    @BindView(R.id.location)
    TextView tvLocation;
    @BindView(R.id.icon)
    ImageView ivIcon;
    @BindView(R.id.label)
    TextView tvLabel;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    private int ENABLE_LOCATION = 1001;
    private LocationManager locationManager;

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        float heightDp = getResources().getDisplayMetrics().heightPixels * 4 / 10;
        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams();
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        lp.height = (int) heightDp;
        AppBarLayout.Behavior behavior = new AppBarLayout.Behavior();
        behavior.setDragCallback(new AppBarLayout.Behavior.DragCallback() {
            @Override
            public boolean canDrag(AppBarLayout appBarLayout) {
                return false;
            }
        });
        lp.setBehavior(behavior);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel.class);
        viewModel.isLoading().observe(this, this::setProgress);
        viewModel.getErrorMsg().observe(this, this::showError);
        viewModel.getCurrentWeather().observe(this, this::setCurrentWeather);
        viewModel.getForecastData().observe(this, this::setForeCastData);
        viewModel.getCurrentLocation().observe(this, this::setCurrentLocation);
        swipeRefreshLayout.setOnRefreshListener(() -> viewModel.refresh());
        // Acquire a reference to the system Location Manager
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        requestLocation();

    }

    void requestLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            new AlertDialog.Builder(this)
                    .setTitle("Location permission required")
                    .setMessage("Location permission required to get the weather details of your place")
                    .setPositiveButton("OK", (dialogInterface, i) -> {
                        dialogInterface.dismiss();

                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                                ENABLE_LOCATION);
                    })
                    .setNegativeButton("Cancel", (dialogInterface, i) -> {
                        dialogInterface.dismiss();
                        finish();
                    }).create().show();
            return;
        }
        swipeRefreshLayout.setRefreshing(true);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 100, locationListener);
    }

    private void setCurrentLocation(Location location) {
        if (location != null) {
            getSupportActionBar().setTitle(location.getName() );
            tvLocation.setText(location.getName() + ", " + location.getRegion());
        }
    }

    private void setCurrentWeather(Current current) {
        if (current != null) {
            tvCurrentTemp.setText(String.valueOf(current.getTempC()) + "°C");
            Glide.with(this).load("https:" + current.getCondition().getIcon())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(ivIcon);
            tvCondition.setText(current.getCondition().getText());
        }
    }

    private void setForeCastData(List<Forecastday> list) {
        if (list != null) {
            recyclerView.setVisibility(View.VISIBLE);
            tvLabel.setVisibility(View.VISIBLE);
            tvLabel.setAlpha(0);
            tvLabel.animate().alpha(1).setDuration(600);
            recyclerView.setY(Utils.getScreenHeight(this));
            recyclerView.animate().translationY(0).setDuration(800).setInterpolator(new DecelerateInterpolator());
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            ForeCastAdapter adapter = new ForeCastAdapter(this, list);
            recyclerView.setLayoutManager(layoutManager);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                    layoutManager.getOrientation());
//            recyclerView.addItemDecoration(dividerItemDecoration);
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
    }

    private void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
    }


    // Define a listener that responds to location updates
    LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(android.location.Location location) {
            viewModel.onLocChanged(location);
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ENABLE_LOCATION) {
            if (resultCode == RESULT_CANCELED) {
                finish();
            } else {
                requestLocation();
            }
        }
    }

}
