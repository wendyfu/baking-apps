package io.github.wendyfu.bakingapp.recipedetail.presentation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.github.wendyfu.bakingapp.R;
import io.github.wendyfu.bakingapp.data.model.RecipeStep;

import static io.github.wendyfu.bakingapp.di.modules.ActivityModule.ACTIVITY_CONTEXT;

/**
 * @author wendy
 * @since Sep 13, 2017.
 */

public class RecipeStepsAdapter extends RecyclerView.Adapter<RecipeDetailViewHolder.Steps> {

    private Context context;
    private List<RecipeStep> steps;
    private OnClickListener listener;

    @Inject public RecipeStepsAdapter(@Named(ACTIVITY_CONTEXT) Context context) {
        this.context = context;
        this.steps = new ArrayList<>();
    }

    @Override
    public RecipeDetailViewHolder.Steps onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_steps, parent, false);
        return new RecipeDetailViewHolder.Steps(view);
    }

    @Override public void onBindViewHolder(RecipeDetailViewHolder.Steps holder, int position) {
        holder.bind(context, steps.get(position), listener);
    }

    @Override public int getItemCount() {
        return steps.size();
    }

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    public void setStepsData(List<RecipeStep> steps) {
        this.steps = steps;
        notifyDataSetChanged();
    }

    interface OnClickListener {

        void onClick(RecipeStep recipeStep);
    }
}
