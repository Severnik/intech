package com.intechtest.ui.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.intechtest.R;
import com.intechtest.controller.DataManager;
import com.intechtest.controller.transit.FragmentAction;
import com.intechtest.model.TextSearch;
import com.intechtest.model.Track;
import com.intechtest.ui.adapter.RecyclerViewAdapterTracks;
import com.intechtest.ui.annotation.Layout;
import com.intechtest.ui.event.OnItemClick;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Server Kurtnebiev on 09.07.2016.
 * Copyright (c) 2016 intech
 */
@Layout(R.layout.fragm_tracks)
public class TracksFragment extends AbstractFragment {

    private static final int gridPreview = R.layout.sounds_grid_item;
    private static final int listPreview = R.layout.sounds_list_item;

    private TextSearch searchWord;

    @BindView(R.id.tracks)
    RecyclerView tracks;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    private RecyclerViewAdapterTracks adapterTracks;
    private int iconState;
    private int layoutState;

    private OnItemClick<Track> onItemClick = new OnItemClick<Track>() {
        @Override
        public void onClick(Track item, int position) {
            getTransitManager().switchBy(FragmentAction.PLAY_PAGE, item.toBundle());
        }
    };

    @Override
    protected void mappingArguments() {
        if (getArguments() != null) {
            searchWord = (TextSearch) getArguments().getSerializable(TextSearch.BUNDLE_KEY);
        }
    }

    @OnClick(R.id.fab)
    void onLayoutChange() {
        switch (iconState) {
            case R.drawable.ic_view_list_black_24dp:
                iconState = R.drawable.ic_view_module_black_24dp;
                layoutState = listPreview;
                setLayoutManager(new LinearLayoutManager(getActivity()));
                break;
            default:
                iconState = R.drawable.ic_view_list_black_24dp;
                layoutState = gridPreview;
                setLayoutManager(new GridLayoutManager(getActivity(),
                        3, LinearLayoutManager.VERTICAL, false));
                break;
        }
        fab.setImageResource(iconState);
    }

    private void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        tracks.setLayoutManager(layoutManager);
        adapterTracks = new RecyclerViewAdapterTracks(adapterTracks.getData());
        adapterTracks.setResources(layoutState);
        adapterTracks.setOnItemClick(onItemClick);
        tracks.setAdapter(adapterTracks);
    }

    @Override
    protected void initView(View view) {
        if (layoutState == 0) {
            layoutState = gridPreview;
            adapterTracks = new RecyclerViewAdapterTracks();
            tracks.setLayoutManager(new GridLayoutManager(getActivity(), 3,
                    LinearLayoutManager.VERTICAL, false));
            adapterTracks.setResources(layoutState);
        } else {
            checkWitchLayoutManagerUse(layoutState == gridPreview);
            adapterTracks.setResources(layoutState);
        }
        if (iconState == 0) {
            iconState = R.drawable.ic_view_list_black_24dp;
        }
        fab.setImageResource(iconState);
        tracks.setAdapter(adapterTracks);
        adapterTracks.setOnItemClick(onItemClick);
        if (adapterTracks.getItemCount() == 0) {
            initSearch();
        }
    }

    private void checkWitchLayoutManagerUse(boolean isGridPreview) {
        tracks.setLayoutManager(isGridPreview ?
                new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false)
                : new LinearLayoutManager(getActivity()));
    }

    private void initSearch() {
        DataManager.tracks(new SimpleIRemoteServiceCallback<List<Track>>() {
            @Override
            public void onSuccess(List<Track> result) {
                adapterTracks.setData(result);
                tracks.refreshDrawableState();
            }
        }, searchWord.getTextSearch());
    }
}
