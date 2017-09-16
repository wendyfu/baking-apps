package io.github.wendyfu.bakingapp.recipestep;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

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

    @BindView(R.id.exoview_step) SimpleExoPlayerView simpleExoPlayerView;
    @Nullable @BindView(R.id.text_step_description) TextView textStepDesc;
    @Nullable @BindView(R.id.text_step_page) TextView textStepPage;
    @Nullable @BindView(R.id.step_nav_container) LinearLayout navigator;

    private int stepId;
    private List<RecipeStep> steps;

    private DefaultBandwidthMeter bandwidthMeter;
    private DefaultTrackSelector trackSelector;
    private DataSource.Factory mediaDataSourceFactory;
    private SimpleExoPlayer player;

    private int resumeWindow;
    private long resumePosition;

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

    private void showData(int stepId, List<RecipeStep> steps) {
        Uri videoUri = Uri.parse(steps.get(stepId).getVideoUrl());
        setupVideoPlayer(videoUri);

        boolean isPhone = !getResources().getBoolean(R.bool.isTablet);
        boolean isLand = getResources().getBoolean(R.bool.isLandscape);
        if (isPhone && isLand) return;

        textStepDesc.setText(steps.get(stepId).getDescription());
        textStepPage.setText(
            String.format(getString(R.string.text_recipe_step_pages), stepId + 1, steps.size()));
    }

    private void setupVideoPlayer(Uri videoUri) {
        bandwidthMeter = new DefaultBandwidthMeter();

        TrackSelection.Factory adaptiveTrackSelectionFactory =
            new AdaptiveTrackSelection.Factory(bandwidthMeter);
        trackSelector = new DefaultTrackSelector(adaptiveTrackSelectionFactory);
        player = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);

        MediaSource mediaSource = buildMediaSource(videoUri);
        simpleExoPlayerView.setPlayer(player);
        player.prepare(mediaSource);
    }

    private DefaultHttpDataSourceFactory buildHttpDataSourceFactory(
        DefaultBandwidthMeter bandwidthMeter) {
        String userAgent = getContext().getPackageName();
        return new DefaultHttpDataSourceFactory(userAgent, bandwidthMeter);
    }

    private MediaSource buildMediaSource(Uri videoUri) {
        mediaDataSourceFactory = new DefaultDataSourceFactory(getContext(), null,
            buildHttpDataSourceFactory(bandwidthMeter));
        return new ExtractorMediaSource(videoUri, mediaDataSourceFactory,
            new DefaultExtractorsFactory(), null, null);
    }

    @Override public void onResume() {
        super.onResume();
        showData(stepId, steps);
    }

    @Override public void onPause() {
        super.onPause();
        if (player != null) {
            player.release();
        }
    }

    @Optional @OnClick(R.id.img_prev_step) public void onClickPrevStep() {
        if (stepId == 0) return;
        stepId -= 1;
        if (player != null) {
            player.release();
        }
        showData(stepId, steps);
    }

    @Optional @OnClick(R.id.img_next_step) public void onClickNextStep() {
        if (stepId == (steps.size() - 1)) return;
        stepId += 1;
        if (player != null) {
            player.release();
        }
        showData(stepId, steps);
    }
}
