package com.intechtest.controller.newtwork.event;

import com.intechtest.RetrofitApi;
import com.intechtest.model.Track;

import java.util.List;

/**
 * Created by Ekaterina Bogolepova on 02.08.2016.
 * Copyright (c) 2016 intech
 */
public class TracksService extends AbstractRestService<List<Track>> {

    private String search;

    public TracksService(String search) {
        this.search = search;
    }

    @Override
    protected void run(RetrofitApi API) {
        serviceResponseObject = API.getTracks(search);
    }
}
