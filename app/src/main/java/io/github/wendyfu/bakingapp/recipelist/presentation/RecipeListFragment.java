package io.github.wendyfu.bakingapp.recipelist.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
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
import io.github.wendyfu.bakingapp.data.model.Recipe;
import io.github.wendyfu.bakingapp.di.components.RecipeComponent;
import io.github.wendyfu.bakingapp.recipedetail.presentation.RecipeDetailActivity;

public class RecipeListFragment extends BaseFragment
    implements RecipeListContract.View, RecipeListAdapter.OnClickListener {

    private static final int GRID_COL_COUNT_TABLET_PORTRAIT = 2;
    private static final int GRID_COL_COUNT_TABLET_LANDSCAPE = 4;

    @BindView(R.id.rv_recipe_list) RecyclerView rvRecipeList;

    @Inject RecipeListPresenter presenter;
    @Inject RecipeListAdapter adapter;

    public RecipeListFragment() {
        setRetainInstance(true);
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(RecipeComponent.class).inject(this);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        ButterKnife.bind(this, view);
        setupRecyclerView();
        return view;
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.setView(this);
        if (savedInstanceState == null) {
            presenter.getRecipeList();
        }
    }

    @Override public void onDestroy() {
        super.onDestroy();
        presenter.dropView();
        rvRecipeList.setAdapter(null);
    }

    private void setupRecyclerView() {
        boolean isTablet = getResources().getBoolean(R.bool.isTablet);
        boolean isLandscape = getResources().getBoolean(R.bool.isLandscape);

        if (isTablet && !isLandscape) {
            rvRecipeList.setLayoutManager(
                new GridLayoutManager(getContext(), GRID_COL_COUNT_TABLET_PORTRAIT));
        } else if (isTablet && isLandscape) {
            rvRecipeList.setLayoutManager(
                new GridLayoutManager(getContext(), GRID_COL_COUNT_TABLET_LANDSCAPE));
        } else {
            rvRecipeList.setLayoutManager(new LinearLayoutManager(getContext()));
        }

        adapter.setOnClickListener(this);
        rvRecipeList.setAdapter(adapter);
    }

    @Override public void click(Recipe recipe) {
        startActivity(new Intent(getContext(), RecipeDetailActivity.class));
    }

    @Override public void addRecipeList(Recipe recipe) {
        adapter.add(recipe);
    }

    @Override public void showErrorGetRecipeList() {

    }
}
