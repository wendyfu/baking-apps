package io.github.wendyfu.bakingapp.recipelist;

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
import dagger.android.support.DaggerFragment;
import io.github.wendyfu.bakingapp.R;
import io.github.wendyfu.bakingapp.di.ActivityScoped;

@ActivityScoped public class RecipeListFragment extends DaggerFragment
    implements RecipeListContract.View {

    @BindView(R.id.rv_recipe_list) RecyclerView rvRecipeList;

    @Inject RecipeListContract.Presenter presenter;

    private RecipeListAdapter adapter;

    @Inject public RecipeListFragment() {
        super();
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
