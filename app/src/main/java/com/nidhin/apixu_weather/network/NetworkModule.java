package com.nidhin.apixu_weather.network;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import com.nidhin.apixu_weather.network.backend.APIModule;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @author Ashwini Kumar.
 */
@Module(includes = APIModule.class)
public class NetworkModule {
    private static final int TIMEOUT_IN_MS = 30000;
    public static final String WEATHER_APP_BASE_URL = "base_url";
    private static final String BASE_URL = "https://api.apixu.com/v1/";

    @Provides
    @Named(WEATHER_APP_BASE_URL)
    String provideBaseUrlString() {
        return BASE_URL;
    }


    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        return new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_IN_MS, TimeUnit.MILLISECONDS)
                .addInterceptor(new ApiInterceptor())
                .cache(cache)
                .build();
    }


    @Provides
    @Singleton
    Cache provideCache(Context context) {
        final int cacheSize = 5 * 1024 * 1024; // 5 MB
        File cacheDir = context.getCacheDir();
        return new Cache(cacheDir, cacheSize);
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

}
