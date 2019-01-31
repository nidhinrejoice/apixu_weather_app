package com.nidhin.apixu_weather.data.api;

import com.nidhin.apixu_weather.data.model.WeatherResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("forecast.json")
    Single<WeatherResponse> getForecast( @Query("days") String days, @Query("q") String query);

}
