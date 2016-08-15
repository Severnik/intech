package com.intechtest;

import android.test.AndroidTestCase;

import com.intechtest.controller.newtwork.event.TracksService;
import com.intechtest.controller.newtwork.model.JSONResponse;
import com.intechtest.model.Track;

import java.util.List;

/**
 * Created by Server Kurtnebiev on 02.08.2016.
 * Copyright (c) 2016 intech
 */
public class TestHandlerAPI extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testTracksHandler() throws Exception {
        String search = "californication";
        JSONResponse<List<Track>> jsonResponse = doTracksRequest(search);
        assertNotNull(jsonResponse);
        assertNotNull(jsonResponse.getResult());
        assertNull(jsonResponse.getError());


    }

    private JSONResponse<List<Track>> doTracksRequest(String search) {
        TracksService service = new TracksService(search);
        service.run();
        return service.getServiceResponseObject();
    }
}
