package io.github.wendyfu.bakingapp.data.model.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import io.github.wendyfu.bakingapp.data.model.Recipe;
import io.github.wendyfu.bakingapp.data.model.RecipeList;

/**
 * @author wendy
 * @since Sep 04, 2017.
 */

public class RecipeListDeserializer implements JsonDeserializer<RecipeList> {

    @Override public RecipeList deserialize(JsonElement json, Type typeOfT,
        JsonDeserializationContext context) throws JsonParseException {
        RecipeList recipeList = new RecipeList();
        Type collectionType = new TypeToken<Collection<Recipe>>() { }.getType();
        recipeList.setRecipeList((List<Recipe>) new Gson().fromJson(json, collectionType));
        return recipeList;
    }
}
