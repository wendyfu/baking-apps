package io.github.wendyfu.bakingapp;

import android.app.Application;

import io.github.wendyfu.bakingapp.di.components.ApplicationComponent;
import io.github.wendyfu.bakingapp.di.components.DaggerApplicationComponent;
import io.github.wendyfu.bakingapp.di.modules.ApplicationModule;

/**
 * @author wendy
 * @since Sep 02, 2017.
 */

public class BakingAppApplication extends Application {

    private static ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(new ApplicationModule(this))
            .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
