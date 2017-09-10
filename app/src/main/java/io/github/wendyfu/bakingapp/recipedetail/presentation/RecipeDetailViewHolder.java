package io.github.wendyfu.bakingapp.recipedetail.presentation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.wendyfu.bakingapp.R;
import io.github.wendyfu.bakingapp.data.model.RecipeIngredient;

/**
 * @author wendy
 * @since Sep 10, 2017.
 */

public class RecipeDetailViewHolder {

    public static class Ingredient extends RecyclerView.ViewHolder {

        @BindView(R.id.text_ingredient) TextView textIngredientName;
        @BindView(R.id.text_quantity_measure) TextView textIngredientQtyMeasure;

        public Ingredient(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(RecipeIngredient recipeIngredient) {
            textIngredientName.setText("KEMPEL");
            textIngredientQtyMeasure.setText("KOPROT");
        }
    }

    public class Steps extends RecyclerView.ViewHolder {

        public Steps(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
