package io.github.wendyfu.bakingapp.widget.presentation;

import javax.inject.Inject;

import io.github.wendyfu.bakingapp.base.domain.DefaultSubscriber;
import io.github.wendyfu.bakingapp.data.model.Recipe;
import io.github.wendyfu.bakingapp.recipelist.domain.GetRecipeListUseCase;

/**
 * @author wendy
 * @since Sep 16, 2017.
 */

public class SimpleRecipeWidgetPresenter implements SimpleRecipeWidgetContract.Presenter {

    private SimpleRecipeWidgetContract.View view;
    private GetRecipeListUseCase getRecipeListUseCase;

    @Inject public SimpleRecipeWidgetPresenter(GetRecipeListUseCase getRecipeListUseCase) {
        this.getRecipeListUseCase = getRecipeListUseCase;
    }

    @Override public void dropView() {
        getRecipeListUseCase.unsubscribe();
    }

    @Override public void setView(SimpleRecipeWidgetContract.View view) {
        this.view = view;
    }

    @Override public void getRecipeList() {
        getRecipeListUseCase.execute(new DefaultSubscriber<Recipe>() {
            @Override public void onNext(Recipe recipe) {
                super.onNext(recipe);
                view.addRecipeList(recipe);
            }

            @Override public void onError(Throwable e) {
                super.onError(e);
                view.showErrorGetRecipeList();
            }
        }, null);
    }
}
