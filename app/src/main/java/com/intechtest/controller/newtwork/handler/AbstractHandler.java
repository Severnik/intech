package com.intechtest.controller.newtwork.handler;

import android.os.AsyncTask;

/**
 * Created by Server Kurtnebiev on 01.08.2016.
 * Copyright (c) 2016 intech
 */
public abstract class AbstractHandler<T> extends AsyncTask<Void, Void, T> {

    @Override
    protected T doInBackground(Void... voids) {
        return executeRoutine(voids);
    }

    protected T executeRoutine(Void... voids) {
        return null;
    }

}
