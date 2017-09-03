package io.github.wendyfu.bakingapp.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author wendy
 * @since Sep 02, 2017.
 */

@Singleton @Component(modules = {
    AndroidSupportInjectionModule.class, ActivityBindingModule.class
}) public interface AppComponent extends AndroidInjector<DaggerApplication> {

    @Override void inject(DaggerApplication instance);

    @Component.Builder interface Builder {

        @BindsInstance AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
