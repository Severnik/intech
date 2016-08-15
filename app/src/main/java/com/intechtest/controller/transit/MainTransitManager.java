package com.intechtest.controller.transit;

import android.app.Activity;

import com.fsm.transit.core.AbstractTransitManager;
import com.fsm.transit.core.TransitData;
import com.fsm.transit.core.TransitResultData;
import com.intechtest.ui.fragment.PlayFragment;
import com.intechtest.ui.fragment.SearchFragment;
import com.intechtest.ui.fragment.TracksFragment;

/**
 * Created by Server Kurtnebiev on 09.07.2016.
 * Copyright (c) 2016 intech
 */
public class MainTransitManager extends AbstractTransitManager<FragmentAction> {

    public MainTransitManager(Activity activity) {
        super(activity);
    }

    {
        transitionsMap.put(new TransitData<FragmentAction>(SearchFragment.class, FragmentAction.TRACKS_PAGE),
                new TransitResultData<FragmentAction>(TracksFragment.class, true));
        transitionsMap.put(new TransitData<FragmentAction>(TracksFragment.class, FragmentAction.PLAY_PAGE),
                new TransitResultData<FragmentAction>(PlayFragment.class, true));
    }
}
