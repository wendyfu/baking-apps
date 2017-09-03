package io.github.wendyfu.bakingapp.recipelist;

import android.os.Bundle;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;
import io.github.wendyfu.bakingapp.R;
import io.github.wendyfu.bakingapp.util.ActivityUtil;

public class RecipeListActivity extends DaggerAppCompatActivity {

    @Inject Lazy<RecipeListFragment> recipeListFragmentProvider;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecipeListFragment recipeListFragment =
            (RecipeListFragment) getSupportFragmentManager().findFragmentById(R.id.frame_fragment);
        if (recipeListFragment == null) {
            recipeListFragment = recipeListFragmentProvider.get();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), recipeListFragment,
                R.id.frame_fragment);
        }
    }
}
