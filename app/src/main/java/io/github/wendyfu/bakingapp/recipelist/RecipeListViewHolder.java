package io.github.wendyfu.bakingapp.recipelist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.wendyfu.bakingapp.R;
import io.github.wendyfu.bakingapp.data.Recipe;

/**
 * @author wendy
 * @since Aug 29, 2017.
 */

class RecipeListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.recipe_image) ImageView imgRecipe;
    @BindView(R.id.recipe_title_text) TextView txtRecipeName;

    RecipeListViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    void bind(Recipe recipe, Context context) {
        txtRecipeName.setText("Cookies");
        Glide.with(context).load(R.drawable.img_default_recipe).into(imgRecipe);
    }
}
