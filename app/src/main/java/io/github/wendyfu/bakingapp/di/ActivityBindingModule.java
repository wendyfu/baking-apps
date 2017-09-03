package io.github.wendyfu.bakingapp.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import io.github.wendyfu.bakingapp.recipelist.RecipeListActivity;
import io.github.wendyfu.bakingapp.recipelist.RecipeListModule;

/**
 * @author wendy
 * @since Sep 02, 2017.
 */

@Module public abstract class ActivityBindingModule {

    @ActivityScoped @ContributesAndroidInjector(modules = RecipeListModule.class)
    abstract RecipeListActivity recipeListActivity();
}
