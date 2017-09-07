package io.github.wendyfu.bakingapp.recipelist.presentation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.github.wendyfu.bakingapp.R;
import io.github.wendyfu.bakingapp.data.model.Recipe;

/**
 * @author wendy
 * @since Aug 29, 2017.
 */

class RecipeListAdapter extends RecyclerView.Adapter<RecipeListViewHolder> {

    private Context context;
    private List<Recipe> recipeList;
    private OnClickListener listener;

    RecipeListAdapter(Context context, OnClickListener listener) {
        super();
        this.context = context;
        this.recipeList = new ArrayList<>();
        this.listener = listener;
    }

    @Override public RecipeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recipe_list, parent, false);
        return new RecipeListViewHolder(view);
    }

    @Override public void onBindViewHolder(RecipeListViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.bind(recipe, listener, context);
    }

    @Override public int getItemCount() {
        return recipeList.size();
    }

    public void add(Recipe recipe) {
        recipeList.add(recipe);
    }

    interface OnClickListener {

        void click(Recipe recipe);
    }
}
