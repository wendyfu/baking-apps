package io.github.wendyfu.bakingapp.recipedetail.presentation;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.parceler.Parcels;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.wendyfu.bakingapp.R;
import io.github.wendyfu.bakingapp.base.presentation.BaseFragment;
import io.github.wendyfu.bakingapp.data.model.Recipe;
import io.github.wendyfu.bakingapp.di.components.RecipeComponent;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

/**
 * @author wendy
 * @since Sep 10, 2017.
 */

public class RecipeDetailFragment extends BaseFragment {

    @BindView(R.id.text_servings) TextView textServings;
    @BindView(R.id.rv_recipe_ingredients) RecyclerView rvIngredients;
    @BindView(R.id.rv_recipe_steps) RecyclerView rvSteps;

    @Inject RecipeIngredientsAdapter ingredientsAdapter;
    @Inject RecipeStepsAdapter stepsAdapter;

    private Recipe recipe;

    public RecipeDetailFragment() {
        setRetainInstance(true);
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(RecipeComponent.class).inject(this);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.bind(this, view);
        setupIngredientRecyclerView();
        setupStepRecyclerView();
        return view;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            recipe = Parcels.unwrap(
                getActivity().getIntent().getParcelableExtra(RecipeDetailActivity.BUNDLE_RECIPE));
        }
        showData(recipe);
    }

    private void setupIngredientRecyclerView() {
        rvIngredients.setLayoutManager(new LinearLayoutManager(getContext()));
        rvIngredients.setAdapter(ingredientsAdapter);
    }

    private void setupStepRecyclerView() {
        rvSteps.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSteps.addItemDecoration(new DividerItemDecoration(getContext(), VERTICAL));
        rvSteps.setAdapter(stepsAdapter);
    }

    private void showData(Recipe recipe) {
        textServings.setText(
            String.format(getString(R.string.text_recipe_servings), recipe.getServings()));
        ingredientsAdapter.setIngredientData(recipe.getIngredients());
        stepsAdapter.setStepsData(recipe.getSteps());
    }
}
