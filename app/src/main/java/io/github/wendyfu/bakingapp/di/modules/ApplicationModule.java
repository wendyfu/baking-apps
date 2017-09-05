package io.github.wendyfu.bakingapp.di.modules;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.wendyfu.bakingapp.BakingAppApplication;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author wendy
 * @since Sep 04, 2017.
 */

@Module public class ApplicationModule {

    public static final String NAME_SCHEDULER_IO = "schedulerIo";
    public static final String NAME_UI_THREAD = "uiThread";

    private final BakingAppApplication application;

    public ApplicationModule(BakingAppApplication application) {
        this.application = application;
    }

    @Provides @Singleton Context provideApplicationContext() {
        return this.application;
    }

    @Provides @Singleton @Named(NAME_SCHEDULER_IO) public Scheduler provideSchedulerIo() {
        return Schedulers.io();
    }

    @Provides @Singleton @Named(NAME_UI_THREAD) public Scheduler provideUiThread() {
        return AndroidSchedulers.mainThread();
    }
}
