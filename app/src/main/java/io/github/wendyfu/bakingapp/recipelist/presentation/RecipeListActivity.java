package io.github.wendyfu.bakingapp.recipelist.presentation;

import android.os.Bundle;

import io.github.wendyfu.bakingapp.BaseActivity;
import io.github.wendyfu.bakingapp.R;
import io.github.wendyfu.bakingapp.di.HasComponent;
import io.github.wendyfu.bakingapp.di.components.DaggerRecipeComponent;
import io.github.wendyfu.bakingapp.di.components.RecipeComponent;

public class RecipeListActivity extends BaseActivity implements HasComponent<RecipeComponent> {

    private RecipeComponent recipeComponent;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initializeInjector();
        this.initializeActivity(savedInstanceState);
    }

    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState != null) return;
        addFragment(R.id.frame_fragment, new RecipeListFragment());
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
