package io.github.wendyfu.bakingapp.data.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * @author wendy
 * @since Aug 29, 2017.
 */

@Parcel
public class RecipeIngredient {

    @SerializedName("quantity") String quantity;
    @SerializedName("measure") String measure;
    @SerializedName("ingredient") String ingredient;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
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
