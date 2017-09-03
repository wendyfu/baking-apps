package io.github.wendyfu.bakingapp.recipelist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.github.wendyfu.bakingapp.R;
import io.github.wendyfu.bakingapp.data.Recipe;

/**
 * @author wendy
 * @since Aug 29, 2017.
 */

class RecipeListAdapter extends RecyclerView.Adapter<RecipeListViewHolder> {

    private Context context;
    private List<Recipe> recipeList;

    RecipeListAdapter(Context context) {
        super();
        this.context = context;
        this.recipeList = new ArrayList<>();
        this.recipeList.add(new Recipe());
        this.recipeList.add(new Recipe());
        this.recipeList.add(new Recipe());
        this.recipeList.add(new Recipe());
        this.recipeList.add(new Recipe());
        this.recipeList.add(new Recipe());
    }

    @Override public RecipeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recipe_list, parent, false);
        return new RecipeListViewHolder(view);
    }

    @Override public void onBindViewHolder(RecipeListViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.bind(recipe, context);
    }

    @Override public int getItemCount() {
        return recipeList.size();
    }
}
