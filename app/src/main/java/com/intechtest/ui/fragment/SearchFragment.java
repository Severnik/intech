package com.intechtest.ui.fragment;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import com.intechtest.R;
import com.intechtest.controller.transit.FragmentAction;
import com.intechtest.model.TextSearch;
import com.intechtest.ui.annotation.Layout;
import com.intechtest.ui.utils.Utils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Ekaterina Bogolepova on 02.08.2016.
 * Copyright (c) 2016 intech
 */
@Layout(R.layout.fragm_start)
public class SearchFragment extends AbstractFragment {

    public static final String SEARCH_WORD = "search_word";

    @BindView(R.id.inputLayoutSearch)
    TextInputLayout ilSearch;

    @BindView(R.id.searchTrack)
    EditText searchTrack;


    @OnClick(R.id.beginSearch)
    void onClick() {
        if (Utils.validateSearch(getActivity(), ilSearch, searchTrack.getText().toString())) {
            getTransitManager().switchBy(FragmentAction.TRACKS_PAGE,
                    new TextSearch(searchTrack.getText().toString()).toBundle());
        }
    }
}
