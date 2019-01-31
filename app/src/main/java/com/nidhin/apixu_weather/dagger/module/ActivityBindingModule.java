package com.nidhin.apixu_weather.dagger.module;

import com.nidhin.apixu_weather.home.HomeActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract HomeActivity bindMainActivity();
}
