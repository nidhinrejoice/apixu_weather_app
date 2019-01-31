//package com.nidhin.apixu_weather.data.api;
//
//import com.nidhin.apixu_weather.data.model.WeatherResponse;
//
//import javax.inject.Inject;
//
//import io.reactivex.Single;
//
//public class WeatherRepository {
//
//    private final WeatherService repoService;
//
//    @Inject
//    public WeatherRepository(WeatherService repoService) {
//        this.repoService = repoService;
//    }
//
//    public Single<WeatherResponse> getRepositories() {
//        return repoService.getForecast("4", "Bangalore");
//    }
//
//}
