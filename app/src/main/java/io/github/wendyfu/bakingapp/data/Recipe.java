package io.github.wendyfu.bakingapp.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author wendy
 * @since Aug 29, 2017.
 */

public class Recipe {

    @SerializedName("id") private int id;
    @SerializedName("name") private String name;
    @SerializedName("servings") private int servings;
    @SerializedName("image") private String image;

    private List<RecipeIngredient> ingredients;
    private List<RecipeStep> steps;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<RecipeStep> getSteps() {
        return steps;
    }

    public void setSteps(List<RecipeStep> steps) {
        this.steps = steps;
    }
}
