package io.github.wendyfu.bakingapp.recipedetail.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.parceler.Parcels;

import io.github.wendyfu.bakingapp.R;
import io.github.wendyfu.bakingapp.base.presentation.BaseActivity;
import io.github.wendyfu.bakingapp.data.model.Recipe;
import io.github.wendyfu.bakingapp.di.HasComponent;
import io.github.wendyfu.bakingapp.di.components.DaggerRecipeComponent;
import io.github.wendyfu.bakingapp.di.components.RecipeComponent;

/**
 * @author wendy
 * @since Sep 10, 2017.
 */

public class RecipeDetailActivity extends BaseActivity implements HasComponent<RecipeComponent> {

    protected static final String BUNDLE_RECIPE = "bundle_recipe";

    private RecipeComponent recipeComponent;
    private Recipe recipe;

    public static Intent getCallingIntent(Context context, Recipe recipe) {
        Intent intent = new Intent(context, RecipeDetailActivity.class);
        intent.putExtra(BUNDLE_RECIPE, Parcels.wrap(recipe));
        return intent;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        this.initializeInjector();
        this.initializeActivity(savedInstanceState);
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BUNDLE_RECIPE, Parcels.wrap(recipe));
    }

    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            addFragment(R.id.frame_fragment, new RecipeDetailFragment());
            recipe = Parcels.unwrap(getIntent().getParcelableExtra(BUNDLE_RECIPE));
        } else {
            recipe = Parcels.unwrap(savedInstanceState.getParcelable(BUNDLE_RECIPE));
        }

        getSupportActionBar().setTitle(recipe.getName());
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
