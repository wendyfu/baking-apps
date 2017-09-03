package io.github.wendyfu.bakingapp;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.github.wendyfu.bakingapp.di.AppComponent;
import io.github.wendyfu.bakingapp.di.DaggerAppComponent;

/**
 * @author wendy
 * @since Sep 02, 2017.
 */

public class BakingAppApplication extends DaggerApplication {

    @Override protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }
}
