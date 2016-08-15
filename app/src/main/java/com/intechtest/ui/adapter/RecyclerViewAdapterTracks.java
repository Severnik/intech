package com.intechtest.ui.adapter;

import android.view.ViewGroup;

import com.intechtest.model.Track;
import com.intechtest.ui.holder.TrackHolder;

import java.util.List;


/**
 * Created by Server Kurtnebiev on 10.07.2016.
 * Copyright (c) 2016 intech
 */
public class RecyclerViewAdapterTracks extends AbstractRecyclerViewAdapter<Track, TrackHolder> {

    private int resources;

    public RecyclerViewAdapterTracks() {
    }

    public RecyclerViewAdapterTracks(List<Track> data) {
        super(data);
    }

    public void setResources(int resources) {
        this.resources = resources;
        notifyDataSetChanged();
    }

    @Override
    public TrackHolder onInitViewHolder(ViewGroup parent, int viewType) {
        return new TrackHolder(inflater.inflate(resources, parent, false));
    }
}
