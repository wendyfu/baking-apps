package io.github.wendyfu.bakingapp.di.components;

import android.support.v7.app.AppCompatActivity;

import dagger.Component;
import io.github.wendyfu.bakingapp.di.ActivityScoped;
import io.github.wendyfu.bakingapp.di.modules.ActivityModule;
import io.github.wendyfu.bakingapp.widget.presentation.view.SimpleRecipeWidgetConfigurationActivity;

/**
 * @author wendy
 * @since Sep 04, 2017.
 */

@ActivityScoped
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {

    AppCompatActivity activity();

    void inject(SimpleRecipeWidgetConfigurationActivity simpleRecipeWidgetConfigurationActivity);
}
