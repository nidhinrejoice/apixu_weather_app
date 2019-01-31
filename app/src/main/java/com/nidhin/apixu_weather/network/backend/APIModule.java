package com.nidhin.apixu_weather.network.backend;

import com.google.gson.Gson;
import com.nidhin.apixu_weather.data.api.WeatherService;
import com.nidhin.apixu_weather.network.NetworkModule;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class APIModule {

    @Provides
    @Singleton
    public WeatherService provideService(OkHttpClient okHttpClient,
                                         @Named(NetworkModule.WEATHER_APP_BASE_URL) String baseUrl,
                                         RxJava2CallAdapterFactory rxJava2CallAdapterFactory,
                                         Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .client(okHttpClient)
                .build().create(WeatherService.class);
    }
}
