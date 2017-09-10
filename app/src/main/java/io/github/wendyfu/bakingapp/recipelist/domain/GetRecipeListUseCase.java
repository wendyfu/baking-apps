package io.github.wendyfu.bakingapp.recipelist.domain;

import javax.inject.Inject;
import javax.inject.Named;

import io.github.wendyfu.bakingapp.base.domain.UseCase;
import io.github.wendyfu.bakingapp.data.model.Recipe;
import io.github.wendyfu.bakingapp.data.model.RecipeList;
import io.github.wendyfu.bakingapp.data.source.network.RecipeListService;
import rx.Observable;
import rx.Scheduler;
import rx.functions.Func0;
import rx.functions.Func1;

import static io.github.wendyfu.bakingapp.di.modules.ApplicationModule.NAME_SCHEDULER_IO;
import static io.github.wendyfu.bakingapp.di.modules.ApplicationModule.NAME_UI_THREAD;

/**
 * @author wendy
 * @since Sep 05, 2017.
 */

public class GetRecipeListUseCase extends UseCase<Void, Recipe> {

    private RecipeListService recipeListService;

    @Inject protected GetRecipeListUseCase(@Named(NAME_SCHEDULER_IO) Scheduler executionScheduler,
        @Named(NAME_UI_THREAD) Scheduler postExecutionScheduler,
        RecipeListService recipeListService) {
        super(executionScheduler, postExecutionScheduler);
        this.recipeListService = recipeListService;
    }

    @Override protected Observable<Recipe> buildUseCaseObservable(Void parameter) {
        return Observable.defer(new Func0<Observable<RecipeList>>() {
            @Override public Observable<RecipeList> call() {
                return recipeListService.getRecipeList();
            }
        }).flatMap(new Func1<RecipeList, Observable<Recipe>>() {
            @Override public Observable<Recipe> call(RecipeList recipeList) {
                return Observable.from(recipeList.getRecipeList());
            }
        });
    }
}
