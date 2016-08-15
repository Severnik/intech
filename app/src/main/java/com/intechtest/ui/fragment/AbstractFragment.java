package com.intechtest.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.fsm.transit.core.ITransitManager;
import com.intechtest.controller.newtwork.event.IRemoteServiceCallback;
import com.intechtest.controller.newtwork.model.Error;
import com.intechtest.controller.newtwork.model.Fail;
import com.intechtest.controller.transit.FragmentAction;
import com.intechtest.ui.activity.AbstractActivity;
import com.intechtest.ui.annotation.Layout;
import com.intechtest.ui.dialog.BlockerDialog;

import butterknife.ButterKnife;
import icepick.Icepick;

/**
 * Created by Server Kurtnebiev on 09.07.2016.
 * Copyright (c) 2016 intech
 */
public abstract class AbstractFragment extends Fragment {

    protected BlockerDialog blockerDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        blockerDialog = new BlockerDialog(getActivity());
        mappingArguments();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getViewLayout(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        initView(view);
    }

    protected void initView(View view) {

    }

    protected void mappingArguments() {

    }

    @Override
    public void onPause() {
        super.onPause();
        hideVirtualKeyboard();
    }

    protected int getViewLayout() {
        Layout layout = this.getClass().getAnnotation(Layout.class);
        return layout != null ? layout.value() : 0;
    }

    /**
     * special fragment manager, do all switch use this object.
     *
     * @return instance of {@link ITransitManager}
     */
    public ITransitManager<FragmentAction> getTransitManager() {
        if (getActivity() != null) {
            return ((AbstractActivity) getActivity()).getTransitManager();
        } else {
            return null;
        }
    }

    /**
     * hide keyboard
     */
    protected void hideVirtualKeyboard() {
        if (getActivity().getCurrentFocus() != null) {
            ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        }
    }

    protected class SimpleIRemoteServiceCallback<E> implements IRemoteServiceCallback<E> {

        @Override
        public void onStartTask() {
              blockerDialog.show();
        }

        @Override
        public void onSuccess(E result) {

        }

        @Override
        public void onFailure(Fail error) {

        }

        @Override
        public void onServerError(Error error) {

        }

        @Override
        public void onFinishTask() {
            blockerDialog.dismiss();
        }
    }
}
