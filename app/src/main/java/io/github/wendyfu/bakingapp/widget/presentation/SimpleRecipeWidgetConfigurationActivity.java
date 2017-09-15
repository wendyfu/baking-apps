package io.github.wendyfu.bakingapp.widget.presentation.view;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.wendyfu.bakingapp.R;
import io.github.wendyfu.bakingapp.base.presentation.BaseActivity;
import io.github.wendyfu.bakingapp.data.model.Recipe;
import io.github.wendyfu.bakingapp.data.model.RecipeIngredient;
import io.github.wendyfu.bakingapp.di.HasComponent;
import io.github.wendyfu.bakingapp.di.components.ActivityComponent;
import io.github.wendyfu.bakingapp.di.components.DaggerActivityComponent;
import io.github.wendyfu.bakingapp.recipelist.presentation.RecipeListAdapter;
import io.github.wendyfu.bakingapp.widget.SimpleRecipeWidgetProvider;
import io.github.wendyfu.bakingapp.widget.presentation.SimpleRecipeWidgetContract;
import io.github.wendyfu.bakingapp.widget.presentation.SimpleRecipeWidgetPresenter;

import static io.github.wendyfu.bakingapp.data.Constant.GRID_COL_COUNT_TABLET_LANDSCAPE;
import static io.github.wendyfu.bakingapp.data.Constant.GRID_COL_COUNT_TABLET_PORTRAIT;

/**
 * @author wendy
 * @since Sep 16, 2017.
 */

public class SimpleRecipeWidgetConfigurationActivity extends BaseActivity
    implements HasComponent<ActivityComponent>, SimpleRecipeWidgetContract.View,
    RecipeListAdapter.OnClickListener {

    @BindView(R.id.rv_recipe_list) RecyclerView rvRecipeList;
    @BindView(R.id.container) CoordinatorLayout coordinatorLayout;

    @Inject RecipeListAdapter recipeListAdapter;
    @Inject SimpleRecipeWidgetPresenter presenter;
    private ActivityComponent activityComponent;
    private int appWidgetId;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_recipe_widget_configuration);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            appWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        this.initializeInjector();
        ButterKnife.bind(this);

        getSupportActionBar().setTitle(
            getResources().getString(R.string.text_widget_configuration_activity_title));

        presenter.setView(this);
        setupRecyclerView();
        presenter.getRecipeList();
    }

    @Override public ActivityComponent getComponent() {
        return this.activityComponent;
    }

    public void initializeInjector() {
        this.activityComponent = DaggerActivityComponent.builder()
            .applicationComponent(getApplicationComponent())
            .activityModule(getActivityModule())
            .build();
        activityComponent.inject(this);
    }

    private void setupRecyclerView() {
        boolean isTablet = getResources().getBoolean(R.bool.isTablet);
        boolean isLandscape = getResources().getBoolean(R.bool.isLandscape);

        if (isTablet && !isLandscape) {
            rvRecipeList.setLayoutManager(
                new GridLayoutManager(this, GRID_COL_COUNT_TABLET_PORTRAIT));
        } else if (isTablet && isLandscape) {
            rvRecipeList.setLayoutManager(
                new GridLayoutManager(this, GRID_COL_COUNT_TABLET_LANDSCAPE));
        } else {
            rvRecipeList.setLayoutManager(new LinearLayoutManager(this));
        }

        recipeListAdapter.setOnClickListener(this);
        rvRecipeList.setAdapter(recipeListAdapter);
    }

    @Override public void click(Recipe recipe) {
        String displayText = constructOneLinerIngredientText(recipe.getIngredients());
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);

        SimpleRecipeWidgetProvider.updateAppWidget(this, appWidgetManager, appWidgetId,
            recipe.getName(), displayText);

        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        setResult(RESULT_OK, resultValue);
        finish();
    }

    private String constructOneLinerIngredientText(List<RecipeIngredient> ingredients) {
        StringBuilder stringBuilder = new StringBuilder();
        for (RecipeIngredient ingredient : ingredients) {
            stringBuilder.append(
                String.format(getString(R.string.text_widget_ingredient), ingredient.getQuantity(),
                    ingredient.getMeasure(), ingredient.getIngredient()));
        }
        return stringBuilder.toString();
    }

    @Override public void addRecipeList(Recipe recipe) {
        recipeListAdapter.add(recipe);
    }

    @Override public void showErrorGetRecipeList() {
        Snackbar snackbar = Snackbar.make(coordinatorLayout,
            getResources().getString(R.string.text_error_recipe_list), Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(getResources().getString(R.string.text_retry),
            new View.OnClickListener() {
                @Override public void onClick(View view) {
                    presenter.getRecipeList();
                }
            });
        snackbar.show();
    }
}
