package com.nidhin.apixu_weather.dagger.component;

import android.app.Application;

import com.nidhin.apixu_weather.base.BaseApplication;
import com.nidhin.apixu_weather.dagger.module.ActivityBindingModule;
import com.nidhin.apixu_weather.dagger.module.AppModule;
import com.nidhin.apixu_weather.network.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBindingModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void inject(BaseApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }
}