package io.github.wendyfu.bakingapp.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.wendyfu.bakingapp.BakingAppApplication;

/**
 * @author wendy
 * @since Sep 04, 2017.
 */

@Module public class ApplicationModule {

    private final BakingAppApplication application;

    public ApplicationModule(BakingAppApplication application) {
        this.application = application;
    }

    @Provides @Singleton Context provideApplicationContext() {
        return this.application;
    }
}
