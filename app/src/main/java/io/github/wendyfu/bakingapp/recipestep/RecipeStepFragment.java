package io.github.wendyfu.bakingapp.recipestep;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import io.github.wendyfu.bakingapp.R;
import io.github.wendyfu.bakingapp.base.presentation.BaseFragment;
import io.github.wendyfu.bakingapp.data.model.RecipeStep;
import io.github.wendyfu.bakingapp.di.components.RecipeComponent;

/**
 * @author wendy
 * @since Sep 14, 2017.
 */

public class RecipeStepFragment extends BaseFragment {

    @BindView(R.id.exoview_step) SimpleExoPlayerView exoView;
    @Nullable @BindView(R.id.text_step_description) TextView textStepDesc;
    @Nullable @BindView(R.id.text_step_page) TextView textStepPage;
    @Nullable @BindView(R.id.step_nav_container) LinearLayout navigator;

    private int stepId;
    private List<RecipeStep> steps;

    public RecipeStepFragment() {
        setRetainInstance(true);
    }

    public static RecipeStepFragment newInstance(int stepId, List<RecipeStep> steps) {
        RecipeStepFragment fragment = new RecipeStepFragment();
        fragment.stepId = stepId;
        fragment.steps = steps;
        return fragment;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(RecipeComponent.class).inject(this);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_step, container, false);
        ButterKnife.bind(this, view);

        if (getResources().getBoolean(R.bool.isTablet)) {
            navigator.setVisibility(View.GONE);
        }

        return view;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showData(stepId, steps);
    }

    private void showData(int stepId, List<RecipeStep> steps) {
        boolean isPhone = !getResources().getBoolean(R.bool.isTablet);
        boolean isLand = getResources().getBoolean(R.bool.isLandscape);
        if (isPhone && isLand) return;

        textStepDesc.setText(steps.get(stepId).getDescription());
        textStepPage.setText(
            String.format(getString(R.string.text_recipe_step_pages), stepId + 1, steps.size()));
    }

    @Optional @OnClick(R.id.img_prev_step) public void onClickPrevStep() {
        if (stepId == 0) return;
        stepId -= 1;
        showData(stepId, steps);
    }

    @Optional @OnClick(R.id.img_next_step) public void onClickNextStep() {
        if (stepId == (steps.size() - 1)) return;
        stepId += 1;
        showData(stepId, steps);
    }
}
