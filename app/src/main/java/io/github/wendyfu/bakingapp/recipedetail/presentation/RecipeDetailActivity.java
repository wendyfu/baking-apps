package io.github.wendyfu.bakingapp.recipedetail.presentation;

import android.os.Bundle;

import io.github.wendyfu.bakingapp.R;
import io.github.wendyfu.bakingapp.base.presentation.BaseActivity;
import io.github.wendyfu.bakingapp.di.HasComponent;
import io.github.wendyfu.bakingapp.di.components.DaggerRecipeComponent;
import io.github.wendyfu.bakingapp.di.components.RecipeComponent;

/**
 * @author wendy
 * @since Sep 10, 2017.
 */

public class RecipeDetailActivity extends BaseActivity implements HasComponent<RecipeComponent> {

    private RecipeComponent recipeComponent;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        this.initializeInjector();
        this.initializeActivity(savedInstanceState);
    }

    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState != null) return;
        addFragment(R.id.frame_fragment, new RecipeDetailFragment());
    }

    @Override public RecipeComponent getComponent() {
        return this.recipeComponent;
    }

    public void initializeInjector() {
        this.recipeComponent = DaggerRecipeComponent.builder()
            .applicationComponent(getApplicationComponent())
            .activityModule(getActivityModule())
            .build();
    }
}
