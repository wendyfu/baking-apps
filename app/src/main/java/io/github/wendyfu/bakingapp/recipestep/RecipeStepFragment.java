package io.github.wendyfu.bakingapp.recipestep;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.wendyfu.bakingapp.R;
import io.github.wendyfu.bakingapp.base.presentation.BaseFragment;
import io.github.wendyfu.bakingapp.data.model.Recipe;
import io.github.wendyfu.bakingapp.data.model.RecipeStep;
import io.github.wendyfu.bakingapp.di.components.RecipeComponent;

import static io.github.wendyfu.bakingapp.data.Constant.BUNDLE_RECIPE;
import static io.github.wendyfu.bakingapp.data.Constant.BUNDLE_STEP_ID;

/**
 * @author wendy
 * @since Sep 14, 2017.
 */

public class RecipeStepFragment extends BaseFragment {

    @BindView(R.id.exoview_step) SimpleExoPlayerView exoView;
    @BindView(R.id.text_step_description) TextView textStepDesc;
    @BindView(R.id.text_step_page) TextView textStepPage;

    private int stepId;
    private List<RecipeStep> steps;

    public RecipeStepFragment() {
        setRetainInstance(true);
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(RecipeComponent.class).inject(this);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_step, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            stepId = getActivity().getIntent().getIntExtra(BUNDLE_STEP_ID, 0);
            steps = ((Recipe) Parcels.unwrap(
                getActivity().getIntent().getParcelableExtra(BUNDLE_RECIPE))).getSteps();
        }
        showData(stepId, steps);
    }

    private void showData(int stepId, List<RecipeStep> steps) {
        //BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        //TrackSelection.Factory videoTrackSelectionFactory =
        //    new AdaptiveTrackSelection.Factory(bandwidthMeter);
        //TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        //
        //SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);
        //
        //DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
        //    Util.getUserAgent(getContext(), getString(R.string.app_name)));
        //ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        //String mp4VideoUriString =
        //    !steps.get(stepId).getVideoUrl().isEmpty() ? steps.get(stepId).getVideoUrl()
        //        : steps.get(stepId).getThumbnailUrl();
        //MediaSource videoSource =
        //    new ExtractorMediaSource(Uri.parse(mp4VideoUriString), dataSourceFactory,
        //        extractorsFactory, null, null);
        //player.prepare(videoSource);
        //player.setPlayWhenReady(true);
        //
        //exoView.setPlayer(player);

        textStepDesc.setText(steps.get(stepId).getDescription());
        textStepPage.setText(
            String.format(getString(R.string.text_recipe_step_pages), stepId + 1, steps.size()));
    }

    @OnClick(R.id.img_prev_step) public void onClickPrevStep() {
        if (stepId == 0) return;
        stepId -= 1;
        showData(stepId, steps);
    }

    @OnClick(R.id.img_next_step) public void onClickNextStep() {
        if (stepId == (steps.size() - 1)) return;
        stepId += 1;
        showData(stepId, steps);
    }
}
