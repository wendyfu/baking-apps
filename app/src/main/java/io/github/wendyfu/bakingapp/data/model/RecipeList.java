package io.github.wendyfu.bakingapp.data.model;

import org.parceler.Parcel;

import java.util.List;

/**
 * @author wendy
 * @since Sep 04, 2017.
 */

@Parcel
public class RecipeList {

    List<Recipe> recipeList;

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }
}
