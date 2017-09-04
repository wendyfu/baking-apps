package io.github.wendyfu.bakingapp.recipelist.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.wendyfu.bakingapp.BaseFragment;
import io.github.wendyfu.bakingapp.R;
import io.github.wendyfu.bakingapp.di.components.RecipeComponent;

public class RecipeListFragment extends BaseFragment
    implements RecipeListContract.View {

    @BindView(R.id.rv_recipe_list) RecyclerView rvRecipeList;

    @Inject RecipeListPresenter presenter;

    private RecipeListAdapter adapter;

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(RecipeComponent.class).inject(this);
        adapter = new RecipeListAdapter(getContext());
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        ButterKnife.bind(this, view);
        setupRecyclerView();
        return view;
    }

    @Override public void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    private void setupRecyclerView() {
        rvRecipeList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvRecipeList.setAdapter(adapter);
    }
}
