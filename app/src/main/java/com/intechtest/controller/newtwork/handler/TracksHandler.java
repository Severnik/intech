package com.intechtest.controller.newtwork.handler;

import com.intechtest.controller.newtwork.event.IRemoteServiceCallback;
import com.intechtest.controller.newtwork.event.TracksService;
import com.intechtest.controller.newtwork.model.JSONResponse;
import com.intechtest.model.Track;

import java.util.List;

/**
 * Created by Server Kurtnebiev on 02.08.2016.
 * Copyright (c) 2016 intech
 */
public class TracksHandler extends AbstractSimpleRemoteHandler<List<Track>> {

    private String search;

    public TracksHandler(IRemoteServiceCallback<List<Track>> callback, String search) {
        super(callback);
        this.search = search;
    }

    @Override
    protected JSONResponse<List<Track>> executeRoutine(Void... voids) {
        return executeService(new TracksService(search));
    }
}
