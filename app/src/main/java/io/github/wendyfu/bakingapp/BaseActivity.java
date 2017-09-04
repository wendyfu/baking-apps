package io.github.wendyfu.bakingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.github.wendyfu.bakingapp.di.components.ApplicationComponent;
import io.github.wendyfu.bakingapp.di.modules.ActivityModule;

/**
 * @author wendy
 * @since Sep 04, 2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((BakingAppApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
