package io.github.wendyfu.bakingapp.widget.presentation;

import io.github.wendyfu.bakingapp.base.presentation.BasePresenter;
import io.github.wendyfu.bakingapp.data.model.Recipe;

/**
 * @author wendy
 * @since Sep 16, 2017.
 */

public class SimpleRecipeWidgetContract {

    public interface View {

        void addRecipeList(Recipe recipe);

        void showErrorGetRecipeList();
    }

    interface Presenter extends BasePresenter<View> {

        @Override void setView(View view);

        void getRecipeList();
    }
}
