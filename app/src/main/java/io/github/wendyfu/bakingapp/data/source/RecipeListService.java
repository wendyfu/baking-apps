package io.github.wendyfu.bakingapp.data.source;

import io.github.wendyfu.bakingapp.data.Recipe;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author wendy
 * @since Sep 02, 2017.
 */

public interface RecipeListService {

    @GET("baking.json") Call<Recipe> getRecipeList();
}
