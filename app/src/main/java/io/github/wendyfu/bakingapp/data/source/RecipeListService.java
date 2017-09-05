package io.github.wendyfu.bakingapp.data.source;

import io.github.wendyfu.bakingapp.data.model.RecipeList;
import retrofit2.http.GET;
import rx.Observable;

/**
 * @author wendy
 * @since Sep 02, 2017.
 */

public interface RecipeListService {

    @GET("baking.json") Observable<RecipeList> getRecipeList();
}
