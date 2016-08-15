package com.intechtest.controller;

import android.os.AsyncTask;

import com.intechtest.controller.newtwork.event.IRemoteServiceCallback;
import com.intechtest.controller.newtwork.handler.TracksHandler;
import com.intechtest.model.Track;

import java.util.List;

/**
 * Created by Server Kurtnebiev on 02.08.2016.
 * Copyright (c) 2016 intech
 */
public class DataManager {

    public static AsyncTask tracks(IRemoteServiceCallback<List<Track>> callback, String search) {
        return new TracksHandler(callback, search).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
