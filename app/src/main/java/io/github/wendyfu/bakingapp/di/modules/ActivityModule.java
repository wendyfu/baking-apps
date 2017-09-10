package io.github.wendyfu.bakingapp.di.modules;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import io.github.wendyfu.bakingapp.base.presentation.BaseActivity;
import io.github.wendyfu.bakingapp.di.ActivityScoped;

/**
 * @author wendy
 * @since Sep 04, 2017.
 */

@Module public class ActivityModule {

    private final AppCompatActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides @ActivityScoped AppCompatActivity activity() {
        return this.activity;
    }
}
