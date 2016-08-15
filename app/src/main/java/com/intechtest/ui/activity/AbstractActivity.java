package com.intechtest.ui.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.fsm.transit.core.ITransitManager;
import com.intechtest.controller.transit.FragmentAction;
import com.intechtest.ui.annotation.Layout;

import icepick.Icepick;

/**
 * Created by Server Kurtnebiev on 09.07.2016.
 * Copyright (c) 2016 intech
 */
public abstract class AbstractActivity extends AppCompatActivity {

    protected ITransitManager<FragmentAction> transitManager;

    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        setContentView(getViewLayout());
        transitManager = createTransitManager();
        getTransitManager().setCurrentContainer(getMainFragmentContainerRes());
        if (savedInstanceState == null) {
            getTransitManager().switchFragment(getFragmentForStart());
        }
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    protected int getViewLayout() {
        Layout layout = this.getClass().getAnnotation(Layout.class);
        return layout != null ? layout.value() : 0;
    }

    /**
     * method provide access to transit manager object for current activity
     *
     * @return TransitManager object, what manipulate fragments
     */
    public ITransitManager<FragmentAction> getTransitManager() {
        return transitManager;
    }


    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            getTransitManager().back();
        } else {
            finish();
        }
    }


    /**
     * set fragment for start after activity open
     *
     * @return Fragment for start
     */
    protected abstract Class<? extends Fragment> getFragmentForStart();

    /**
     * create concrete TransitManager object for activity page transitions
     */
    protected abstract ITransitManager<FragmentAction> createTransitManager();


    /**
     * set FrameLayout id for fragment container
     *
     * @return id for transit manager
     */
    protected abstract int getMainFragmentContainerRes();
}
