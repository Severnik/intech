package com.intechtest.ui.fragment;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.intechtest.R;
import com.intechtest.model.Track;
import com.intechtest.ui.annotation.Layout;
import com.intechtest.ui.utils.Utils;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Server Kurtnebiev on 09.07.2016.
 * Copyright (c) 2016 ArelloMobileTest
 */
@Layout(R.layout.fragm_play)
public class PlayFragment extends AbstractFragment implements DiscreteSeekBar.OnProgressChangeListener {

    private static final String TAG = PlayFragment.class.getSimpleName();
    @BindView(R.id.albumView)
    ImageView albumView;

    @BindView(R.id.songProgressBar)
    DiscreteSeekBar songProgressBar;

    @BindView(R.id.spendTime)
    TextView spendTime;

    @BindView(R.id.artistNameAndTrack)
    TextView artistNameAndTrack;

    private Track track;
    private MediaPlayer mp;
    private Handler handler = new Handler();

    @Override
    protected void mappingArguments() {
        if (getArguments() != null) {
            track = (Track) getArguments().getSerializable(Track.BUNDLE_KEY);
        }
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        String title = track.getArtistName() + "\n" + track.getTrackName();
        artistNameAndTrack.setText(title);
        initMediaPlayer();
        if (track != null) {
            Utils.setImageByURI(track.getAlbumImage(), albumView);
        }
    }

    private void initMediaPlayer() {
        mp = new MediaPlayer();
        songProgressBar.setIndicatorPopupEnabled(true); // Important
        songProgressBar.setOnProgressChangeListener(this); // Important
        playSong();
    }

    @OnClick(R.id.play)
    void onClick(View view) {
        ImageButton playBtn = (ImageButton) view;
        if (mp != null) {
            if (mp.isPlaying()) {
                mp.pause();
                playBtn.setBackgroundResource(R.drawable.img_btn_play);
            } else {
                mp.start();
                playBtn.setBackgroundResource(R.drawable.img_btn_pause);
            }
        }
    }

    public void playSong() {
        try {
            mp.reset();
            mp.setDataSource(getActivity(), Uri.parse(track.getTrackUrl()));
            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    blockerDialog.dismiss();
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                    songProgressBar.setProgress(0);
                    songProgressBar.setMax(100);
                    updateProgressBar();
                }
            });
            blockerDialog.show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        mp.prepare();
                    } catch (IOException e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
            }).start();
        } catch (IllegalArgumentException | IllegalStateException | IOException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    /**
     * Update timer on seekbar
     */
    public void updateProgressBar() {
        handler.postDelayed(updateTimeTask, 100);
    }

    /**
     * Background Runnable thread
     */
    private Runnable updateTimeTask = new Runnable() {
        public void run() {
            long totalDuration = mp.getDuration();
            long currentDuration = mp.getCurrentPosition();
            spendTime.setText(String.valueOf(Utils.milliSecondsToTimer(totalDuration)));
            spendTime.setText(String.valueOf(Utils.milliSecondsToTimer(currentDuration)));
            int progress = Utils.getProgressPercentage(currentDuration, totalDuration);
            songProgressBar.setProgress(progress);
            // Running this thread after 100 milliseconds
            handler.postDelayed(this, 100);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(updateTimeTask);
        mp.release();
    }

    @Override
    public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(DiscreteSeekBar seekBar) {
        handler.removeCallbacks(updateTimeTask);
    }

    @Override
    public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
        handler.removeCallbacks(updateTimeTask);
        int totalDuration = mp.getDuration();
        int currentPosition = Utils.progressToTimer(seekBar.getProgress(), totalDuration);
        mp.seekTo(currentPosition);
        updateProgressBar();
    }
}
