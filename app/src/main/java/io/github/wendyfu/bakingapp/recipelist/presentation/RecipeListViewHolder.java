package io.github.wendyfu.bakingapp.recipelist.presentation;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.wendyfu.bakingapp.R;
import io.github.wendyfu.bakingapp.data.model.Recipe;

/**
 * @author wendy
 * @since Aug 29, 2017.
 */

class RecipeListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.card_view_recipe) CardView cardView;
    @BindView(R.id.recipe_image) ImageView imgRecipe;
    @BindView(R.id.recipe_title_text) TextView txtRecipeName;

    RecipeListViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    void bind(final Recipe recipe, final RecipeListAdapter.OnClickListener listener,
        Context context) {
        txtRecipeName.setText(recipe.getName());

        if (recipe.getImage() == null || recipe.getImage().isEmpty()) {
            Glide.with(context).load(R.drawable.img_default_recipe).into(imgRecipe);
        } else {
            Glide.with(context).load(recipe.getImage()).into(imgRecipe);
        }

        if (listener != null) {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View view) {
                    listener.click(recipe);
                }
            });
        }
    }
}
