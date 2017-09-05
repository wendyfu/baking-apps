package io.github.wendyfu.bakingapp.di.components;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import io.github.wendyfu.bakingapp.di.modules.ApplicationModule;
import rx.Scheduler;

import static io.github.wendyfu.bakingapp.di.modules.ApplicationModule.NAME_SCHEDULER_IO;
import static io.github.wendyfu.bakingapp.di.modules.ApplicationModule.NAME_UI_THREAD;

/**
 * @author wendy
 * @since Sep 02, 2017.
 */

@Singleton @Component(modules = {
    ApplicationModule.class
}) public interface ApplicationComponent {

    Context context();

    @Named(NAME_SCHEDULER_IO) Scheduler schedulerIo();

    @Named(NAME_UI_THREAD) Scheduler uiThread();
}
