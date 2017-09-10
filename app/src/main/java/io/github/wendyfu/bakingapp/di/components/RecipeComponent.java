package io.github.wendyfu.bakingapp.di.components;

import dagger.Component;
import io.github.wendyfu.bakingapp.di.ActivityScoped;
import io.github.wendyfu.bakingapp.di.modules.ActivityModule;
import io.github.wendyfu.bakingapp.di.modules.RecipeModule;
import io.github.wendyfu.bakingapp.recipedetail.presentation.RecipeDetailFragment;
import io.github.wendyfu.bakingapp.recipelist.presentation.RecipeListFragment;

/**
 * @author wendy
 * @since Sep 04, 2017.
 */

@ActivityScoped @Component(dependencies = ApplicationComponent.class, modules = {
    ActivityModule.class, RecipeModule.class
}) public interface RecipeComponent extends ActivityComponent {

    void inject(RecipeListFragment recipeListFragment);

    void inject(RecipeDetailFragment recipeDetailFragment);
}
