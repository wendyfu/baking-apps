package io.github.wendyfu.bakingapp.recipelist;

import io.github.wendyfu.bakingapp.BasePresenter;

/**
 * @author wendy
 * @since Sep 02, 2017.
 */

public interface RecipeListContract {

    interface View {

    }

    interface Presenter extends BasePresenter<View> {

        @Override void setView(View view);
    }
}
