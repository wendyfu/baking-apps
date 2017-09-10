package io.github.wendyfu.bakingapp.recipedetail.presentation;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.wendyfu.bakingapp.R;
import io.github.wendyfu.bakingapp.base.presentation.BaseFragment;
import io.github.wendyfu.bakingapp.di.components.RecipeComponent;

/**
 * @author wendy
 * @since Sep 10, 2017.
 */

public class RecipeDetailFragment extends BaseFragment {

    @BindView(R.id.rv_recipe_ingredients) RecyclerView rvIngredients;

    @Inject RecipeIngredientsAdapter ingredientsAdapter;

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
        return view;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void setupIngredientRecyclerView() {
        rvIngredients.setLayoutManager(new LinearLayoutManager(getContext()));
        rvIngredients.setAdapter(ingredientsAdapter);
    }
}
