package io.github.wendyfu.bakingapp.recipelist.presentation;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.github.wendyfu.bakingapp.DefaultSubscriber;
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
        getRecipeListUseCase.execute(new DefaultSubscriber<List<Recipe>>() {
            @Override public void onNext(List<Recipe> recipes) {
                super.onNext(recipes);
                Log.d("WND", recipes.get(0).getName());
            }

            @Override public void onError(Throwable e) {
                super.onError(e);
                Log.d("WND", e.getMessage());
            }
        }, null);
    }
}
