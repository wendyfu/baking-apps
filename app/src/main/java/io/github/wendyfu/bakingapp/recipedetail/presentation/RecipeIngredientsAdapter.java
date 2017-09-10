package io.github.wendyfu.bakingapp.recipedetail.presentation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.github.wendyfu.bakingapp.R;
import io.github.wendyfu.bakingapp.data.model.RecipeIngredient;

/**
 * @author wendy
 * @since Sep 10, 2017.
 */

public class RecipeIngredientsAdapter
    extends RecyclerView.Adapter<RecipeDetailViewHolder.Ingredient> {

    private Context context;
    private List<RecipeIngredient> ingredients;

    @Inject public RecipeIngredientsAdapter(Context context) {
        this.context = context;
        this.ingredients = new ArrayList<>();
        ingredients.add(new RecipeIngredient());
        ingredients.add(new RecipeIngredient());
        ingredients.add(new RecipeIngredient());
        ingredients.add(new RecipeIngredient());
        notifyDataSetChanged();
    }

    @Override
    public RecipeDetailViewHolder.Ingredient onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ingredients, parent, false);
        return new RecipeDetailViewHolder.Ingredient(view);
    }

    @Override public void onBindViewHolder(RecipeDetailViewHolder.Ingredient holder, int position) {
        holder.bind(new RecipeIngredient());
    }

    @Override public int getItemCount() {
        return 5;
    }
}
