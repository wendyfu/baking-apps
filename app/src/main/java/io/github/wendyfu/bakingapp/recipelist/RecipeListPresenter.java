package io.github.wendyfu.bakingapp.recipelist;

import javax.inject.Inject;

/**
 * @author wendy
 * @since Sep 02, 2017.
 */

class RecipeListPresenter implements RecipeListContract.Presenter {

    private RecipeListContract.View recipeListView;

    @Inject RecipeListPresenter() {
        super();
    }

    @Override public void setView(RecipeListContract.View view) {
        this.recipeListView = view;
    }

    @Override public void dropView() {

    }
}
