package com.intechtest.ui.activity;

import android.app.Fragment;

import com.fsm.transit.core.ITransitManager;
import com.intechtest.R;
import com.intechtest.controller.transit.FragmentAction;
import com.intechtest.controller.transit.MainTransitManager;
import com.intechtest.ui.annotation.Layout;
import com.intechtest.ui.fragment.SearchFragment;

/**
 * Created by Server Kurtnebiev on 09.07.2016.
 * Copyright (c) 2016 intech
 */
@Layout(R.layout.activity_main)
public class MainActivity extends AbstractActivity {

    @Override
    protected Class<? extends Fragment> getFragmentForStart() {
        return SearchFragment.class;
    }


    @Override
    protected ITransitManager<FragmentAction> createTransitManager() {
        return new MainTransitManager(this);
    }

    @Override
    protected int getMainFragmentContainerRes() {
        return R.id.fragment_container;
    }

}
