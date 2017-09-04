package io.github.wendyfu.bakingapp.di.components;

import javax.inject.Singleton;

import dagger.Component;
import io.github.wendyfu.bakingapp.di.modules.ApplicationModule;

/**
 * @author wendy
 * @since Sep 02, 2017.
 */

@Singleton @Component(modules = {
    ApplicationModule.class
}) public interface ApplicationComponent {

}
