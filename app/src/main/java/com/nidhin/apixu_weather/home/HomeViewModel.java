package com.nidhin.apixu_weather.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.nidhin.apixu_weather.data.api.WeatherService;
import com.nidhin.apixu_weather.data.model.Current;
import com.nidhin.apixu_weather.data.model.Forecastday;
import com.nidhin.apixu_weather.data.model.Location;
import com.nidhin.apixu_weather.data.model.WeatherResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {
    private static final int EIGHT_O_CLOCK = 20;
    private static final String COUNTRY_US = "US";
    private static final String QUERY_DATE_FORMAT = "yyyy-MM-dd";
    private static final String SCHEDULE_DATE_FORMAT = "EEE, MMM d, ''yy";
    private final MutableLiveData<Location> currentLocation;
    private CompositeDisposable compositeDisposable;
    private WeatherService weatherService;
    private MutableLiveData<Boolean> isLoading;
    private MutableLiveData<List<Forecastday>> forecastList;
    private MutableLiveData<Current> currentWeather;
    private MutableLiveData<String> errorMsg;

    android.location.Location location;
    boolean locationShown;

    @Inject
    HomeViewModel(WeatherService weatherService) {
        this.weatherService = weatherService;
        forecastList = new MutableLiveData<>();
        isLoading = new MutableLiveData<>();
        errorMsg = new MutableLiveData<>();
        currentWeather = new MutableLiveData<>();
        currentLocation = new MutableLiveData<>();
        this.compositeDisposable = new CompositeDisposable();
    }

    public void onLocChanged(android.location.Location location) {
        this.location = location;
        if (!locationShown)
            onScreenCreated();
        locationShown = true;
    }

    public void onScreenCreated() {
        if (location != null) {
            isLoading.setValue(true);
            compositeDisposable.add(weatherService.getForecast("7", String.valueOf(location.getLatitude() + "," + location.getLongitude()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).map(response -> {
                        {
                            currentWeather.setValue(response.getCurrent());
                            currentLocation.setValue(response.getLocation());
                            return response.getForecast().getForecastday();
                        }
                    })
                    .subscribe(this::onSuccess, this::onError));
        }
    }

    void onSuccess(List<Forecastday> list) {
        locationShown = true;
        forecastList.setValue(list);
        isLoading.setValue(false);
    }

    private void onError(Throwable throwable) {
        isLoading.setValue(false);
        errorMsg.setValue(throwable.getMessage());
    }

    private static String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(QUERY_DATE_FORMAT, Locale.US);
        Calendar calendar = Calendar.getInstance();
        return simpleDateFormat.format(calendar.getTime());
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }

    public LiveData<Current> getCurrentWeather() {
        return currentWeather;
    }

    public LiveData<Location> getCurrentLocation() {
        return currentLocation;
    }

    public LiveData<List<Forecastday>> getForecastData() {
        return forecastList;
    }


    public LiveData<String> getErrorMsg() {
        return errorMsg;
    }

    public void refresh() {
        onScreenCreated();
    }
}
