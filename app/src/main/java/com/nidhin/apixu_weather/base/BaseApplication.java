package com.nidhin.apixu_weather.base;

import android.app.Activity;
import android.app.Application;

import com.nidhin.apixu_weather.dagger.component.AppInjector;
import com.nidhin.apixu_weather.dagger.component.ApplicationComponent;
import com.nidhin.apixu_weather.dagger.component.DaggerApplicationComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class BaseApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    private ApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerApplicationComponent.builder()
                .application(this)
                .build();
        appComponent.inject(this);
        AppInjector.init(this);
    }

    public ApplicationComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}