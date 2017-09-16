package io.github.wendyfu.bakingapp.recipelist.presentation;

import io.github.wendyfu.bakingapp.base.presentation.BasePresenter;
import io.github.wendyfu.bakingapp.data.model.Recipe;

/**
 * @author wendy
 * @since Sep 02, 2017.
 */

public interface RecipeListContract {

    interface View {

        void addRecipeList(Recipe recipe);

        void finishGetAllRecipeList();

        void showErrorGetRecipeList();
    }

    interface Presenter extends BasePresenter<View> {

        @Override void setView(View view);

        void getRecipeList();
    }
}
