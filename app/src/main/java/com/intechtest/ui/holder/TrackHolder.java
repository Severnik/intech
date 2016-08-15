package com.intechtest.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.intechtest.R;
import com.intechtest.model.Track;
import com.intechtest.ui.utils.Utils;

import butterknife.BindView;


/**
 * Created by Server Kurtnebiev on 10.07.2016.
 * Copyright (c) 2016 intech
 */
public class TrackHolder extends AbstractHolder<Track> {

    @BindView(R.id.albumView)
    ImageView albumView;

    @BindView(R.id.artistName)
    TextView artistName;

    @BindView(R.id.trackName)
    TextView trackName;

    @BindView(R.id.trackDuration)
    TextView trackDuration;

    public TrackHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(int position) {
        Utils.setImageByURI(model.getAlbumImage(), albumView);
        artistName.setText(model.getArtistName());
        trackName.setText(model.getTrackName());
        trackDuration.setText(String.valueOf(model.getTrackTime()));
    }
}
