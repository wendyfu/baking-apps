package io.github.wendyfu.bakingapp.recipelist.presentation;

import javax.inject.Inject;

import io.github.wendyfu.bakingapp.di.ActivityScoped;

/**
 * @author wendy
 * @since Sep 02, 2017.
 */
@ActivityScoped public class RecipeListPresenter implements RecipeListContract.Presenter {

    private RecipeListContract.View recipeListView;

    @Inject RecipeListPresenter() {
    }

    @Override public void setView(RecipeListContract.View view) {
        this.recipeListView = view;
    }

    @Override public void dropView() {

    }
}
