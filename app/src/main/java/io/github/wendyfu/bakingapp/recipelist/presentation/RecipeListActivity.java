package io.github.wendyfu.bakingapp.recipelist.presentation;

import android.os.Bundle;

import io.github.wendyfu.bakingapp.BaseActivity;
import io.github.wendyfu.bakingapp.R;
import io.github.wendyfu.bakingapp.di.HasComponent;
import io.github.wendyfu.bakingapp.di.components.DaggerRecipeComponent;
import io.github.wendyfu.bakingapp.di.components.RecipeComponent;
import io.github.wendyfu.bakingapp.util.ActivityUtil;

public class RecipeListActivity extends BaseActivity implements HasComponent<RecipeComponent> {

    private RecipeComponent recipeComponent;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initializeInjector();

        RecipeListFragment recipeListFragment =
            (RecipeListFragment) getSupportFragmentManager().findFragmentById(R.id.frame_fragment);
        if (recipeListFragment == null) {
            recipeListFragment = new RecipeListFragment();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), recipeListFragment,
                R.id.frame_fragment);
        }
    }

    public void initializeInjector() {
        this.recipeComponent = DaggerRecipeComponent.builder()
            .applicationComponent(getApplicationComponent())
            .activityModule(getActivityModule())
            .build();
    }

    @Override public RecipeComponent getComponent() {
        return this.recipeComponent;
    }
}
