package io.github.wendyfu.bakingapp.recipestep;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import org.parceler.Parcels;

import io.github.wendyfu.bakingapp.R;
import io.github.wendyfu.bakingapp.base.presentation.BaseActivity;
import io.github.wendyfu.bakingapp.data.model.Recipe;
import io.github.wendyfu.bakingapp.di.HasComponent;
import io.github.wendyfu.bakingapp.di.components.DaggerRecipeComponent;
import io.github.wendyfu.bakingapp.di.components.RecipeComponent;

import static io.github.wendyfu.bakingapp.data.Constant.BUNDLE_RECIPE;
import static io.github.wendyfu.bakingapp.data.Constant.BUNDLE_STEP_ID;

/**
 * @author wendy
 * @since Sep 14, 2017.
 */

public class RecipeStepActivity extends BaseActivity implements HasComponent<RecipeComponent> {

    private RecipeComponent recipeComponent;
    private Recipe recipe;

    public static Intent getCallingIntent(Context context, int stepId, Recipe recipe) {
        Intent intent = new Intent(context, RecipeStepActivity.class);
        intent.putExtra(BUNDLE_STEP_ID, stepId);
        intent.putExtra(BUNDLE_RECIPE, Parcels.wrap(recipe));
        return intent;
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step);
        this.initializeInjector();
        this.initializeActivity(savedInstanceState);
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BUNDLE_RECIPE, Parcels.wrap(recipe));
    }

    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            recipe = Parcels.unwrap(getIntent().getParcelableExtra(BUNDLE_RECIPE));
            int stepId = getIntent().getIntExtra(BUNDLE_STEP_ID, 0);
            addFragment(R.id.frame_fragment,
                RecipeStepFragment.newInstance(stepId, recipe.getSteps()));
        } else {
            recipe = Parcels.unwrap(savedInstanceState.getParcelable(BUNDLE_RECIPE));
        }

        getSupportActionBar().setTitle(recipe.getName());
        getSupportActionBar().setHomeButtonEnabled(true);
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
