package io.github.wendyfu.bakingapp.recipestep;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.exoplayer2.C;
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
    @BindView(R.id.container_video_not_available) FrameLayout containerVideoNotAvailableText;
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
        clearResumePosition();
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
        String videoUrlString =
            (steps.get(stepId).getVideoUrl().isEmpty() ? steps.get(stepId).getThumbnailUrl()
                : steps.get(stepId).getVideoUrl());

        setupVideoPlayer(videoUrlString);

        boolean isPhone = !getResources().getBoolean(R.bool.isTablet);
        boolean isLand = getResources().getBoolean(R.bool.isLandscape);
        if (isPhone && isLand) return;

        textStepDesc.setText(steps.get(stepId).getDescription());
        textStepPage.setText(
            String.format(getString(R.string.text_recipe_step_pages), stepId + 1, steps.size()));
    }

    private void setupVideoPlayer(String videoUriString) {
        if (videoUriString == null || videoUriString.isEmpty()) {
            containerVideoNotAvailableText.setVisibility(View.VISIBLE);
            return;
        }

        containerVideoNotAvailableText.setVisibility(View.GONE);
        Uri videoUri = Uri.parse(videoUriString);
        bandwidthMeter = new DefaultBandwidthMeter();

        TrackSelection.Factory adaptiveTrackSelectionFactory =
            new AdaptiveTrackSelection.Factory(bandwidthMeter);
        trackSelector = new DefaultTrackSelector(adaptiveTrackSelectionFactory);
        player = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);

        MediaSource mediaSource = buildMediaSource(videoUri);
        simpleExoPlayerView.setPlayer(player);

        boolean haveResumePosition = resumeWindow != C.INDEX_UNSET;
        if (haveResumePosition) {
            player.seekTo(resumeWindow, resumePosition);
        }

        player.setPlayWhenReady(true);
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

    private void updateResumePosition() {
        resumeWindow = player.getCurrentWindowIndex();
        resumePosition = Math.max(0, player.getContentPosition());
    }

    private void clearResumePosition() {
        resumeWindow = C.INDEX_UNSET;
        resumePosition = C.TIME_UNSET;
    }

    @Override public void onResume() {
        super.onResume();
        showData(stepId, steps);
    }

    @Override public void onPause() {
        super.onPause();
        if (player != null) {
            player.release();
            updateResumePosition();
        }
    }

    @Optional @OnClick(R.id.img_prev_step) public void onClickPrevStep() {
        if (stepId == 0) return;
        stepId -= 1;
        if (player != null) {
            player.release();
            clearResumePosition();
        }
        showData(stepId, steps);
    }

    @Optional @OnClick(R.id.img_next_step) public void onClickNextStep() {
        if (stepId == (steps.size() - 1)) return;
        stepId += 1;
        if (player != null) {
            player.release();
            clearResumePosition();
        }
        showData(stepId, steps);
    }
}
