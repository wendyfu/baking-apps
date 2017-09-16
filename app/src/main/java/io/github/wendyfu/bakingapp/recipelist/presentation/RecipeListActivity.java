package io.github.wendyfu.bakingapp.recipelist.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;

import io.github.wendyfu.bakingapp.R;
import io.github.wendyfu.bakingapp.base.SimpleIdlingResource;
import io.github.wendyfu.bakingapp.base.presentation.BaseActivity;
import io.github.wendyfu.bakingapp.di.HasComponent;
import io.github.wendyfu.bakingapp.di.components.DaggerRecipeComponent;
import io.github.wendyfu.bakingapp.di.components.RecipeComponent;

public class RecipeListActivity extends BaseActivity
    implements HasComponent<RecipeComponent>, RecipeListFragment.OnCompletedListener {

    private RecipeComponent recipeComponent;

    @Nullable private SimpleIdlingResource idlingResource;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        this.initializeInjector();
        this.initializeActivity(savedInstanceState);
    }

    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState != null) return;
        addFragment(R.id.frame_fragment, new RecipeListFragment());
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

    @Override public void onCompleted() {
        ((SimpleIdlingResource) getIdlingResource()).setIdleState(true);
    }

    @VisibleForTesting @NonNull public IdlingResource getIdlingResource() {
        if (idlingResource == null) {
            idlingResource = new SimpleIdlingResource();
            idlingResource.setIdleState(false);
        }
        return idlingResource;
    }
}
