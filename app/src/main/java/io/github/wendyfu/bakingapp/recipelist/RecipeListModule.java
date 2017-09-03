package io.github.wendyfu.bakingapp.recipelist;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import io.github.wendyfu.bakingapp.di.ActivityScoped;
import io.github.wendyfu.bakingapp.di.FragmentScoped;

/**
 * @author wendy
 * @since Sep 02, 2017.
 */

@Module public abstract class RecipeListModule {

    @FragmentScoped @ContributesAndroidInjector abstract RecipeListFragment recipeListFragment();

    @ActivityScoped @Binds
    abstract RecipeListContract.Presenter recipeListPresenter(RecipeListPresenter presenter);
}
