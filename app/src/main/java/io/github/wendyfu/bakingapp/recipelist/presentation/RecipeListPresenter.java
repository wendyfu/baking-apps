package io.github.wendyfu.bakingapp.recipelist.presentation;

import javax.inject.Inject;

import io.github.wendyfu.bakingapp.base.domain.DefaultSubscriber;
import io.github.wendyfu.bakingapp.data.model.Recipe;
import io.github.wendyfu.bakingapp.di.ActivityScoped;
import io.github.wendyfu.bakingapp.recipelist.domain.GetRecipeListUseCase;

/**
 * @author wendy
 * @since Sep 02, 2017.
 */
@ActivityScoped public class RecipeListPresenter implements RecipeListContract.Presenter {

    private RecipeListContract.View recipeListView;
    private GetRecipeListUseCase getRecipeListUseCase;

    @Inject RecipeListPresenter(GetRecipeListUseCase getRecipeListUseCase) {
        this.getRecipeListUseCase = getRecipeListUseCase;
    }

    @Override public void setView(RecipeListContract.View view) {
        this.recipeListView = view;
    }

    @Override public void dropView() {
        getRecipeListUseCase.unsubscribe();
    }

    @Override public void getRecipeList() {
        getRecipeListUseCase.execute(new DefaultSubscriber<Recipe>() {
            @Override public void onNext(Recipe recipe) {
                super.onNext(recipe);
                recipeListView.addRecipeList(recipe);
            }

            @Override public void onError(Throwable e) {
                super.onError(e);
                recipeListView.showErrorGetRecipeList();
            }
        }, null);
    }
}
