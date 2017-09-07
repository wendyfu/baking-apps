package io.github.wendyfu.bakingapp.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author wendy
 * @since Aug 29, 2017.
 */

public class RecipeIngredient {

    @SerializedName("quantity") private int quantity;
    @SerializedName("measure") private String measure;
    @SerializedName("ingredient") private String ingredient;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
