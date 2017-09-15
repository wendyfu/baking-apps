package io.github.wendyfu.bakingapp.recipedetail.presentation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.wendyfu.bakingapp.R;
import io.github.wendyfu.bakingapp.data.model.RecipeIngredient;
import io.github.wendyfu.bakingapp.data.model.RecipeStep;

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

        public void bind(Context context, RecipeIngredient recipeIngredient) {
            textIngredientName.setText(recipeIngredient.getIngredient());
            textIngredientQtyMeasure.setText(
                String.format(context.getString(R.string.text_recipe_quantity_measure),
                    recipeIngredient.getQuantity(), recipeIngredient.getMeasure()));
        }
    }

    public static class Steps extends RecyclerView.ViewHolder {

        @BindView(R.id.layout_step_container) LinearLayout container;
        @BindView(R.id.text_step_short_desc) TextView textStepShortDescription;

        public Steps(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(Context context, final RecipeStep recipeStep,
            final RecipeStepsAdapter.OnClickListener listener) {
            textStepShortDescription.setText(
                String.format(context.getString(R.string.text_recipe_step_number),
                    recipeStep.getId() + 1, recipeStep.getShortDescription()));
            container.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View view) {
                    listener.onClick(recipeStep);
                }
            });
        }
    }
}
