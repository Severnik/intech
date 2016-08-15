package com.intechtest.controller.newtwork.handler;

import com.intechtest.controller.newtwork.event.IRemoteServiceCallback;
import com.intechtest.controller.newtwork.model.Fail;
import com.intechtest.controller.newtwork.model.JSONResponse;

/**
 * Created by Server Kurtnebiev on 02.08.2016.
 * Copyright (c) 2016 intech
 */
public abstract class AbstractRemoteHandler<T> extends AbstractHandler<JSONResponse<T>> {

    protected IRemoteServiceCallback<T> callback;

    public AbstractRemoteHandler(IRemoteServiceCallback<T> callback) {
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        fireStart();
    }

    @Override
    protected JSONResponse<T> doInBackground(Void... voids) {
        JSONResponse<T> result;
        try {
            result = super.doInBackground(voids);
        } catch (Exception e) {
            result = getFailResponse(e);
        }
        return result;
    }

    protected JSONResponse<T> getFailResponse(Exception e) {
        JSONResponse<T> result = new JSONResponse<T>();
        result.setFail(new Fail(e.getLocalizedMessage(), e));
        return result;
    }

    @Override
    protected void onPostExecute(JSONResponse<T> tServiceResponseObject) {
        super.onPostExecute(tServiceResponseObject);
        postExecute(tServiceResponseObject);
        fireFinish();
    }

    protected void postExecute(JSONResponse<T> tServiceResponseObject) {
        fireResult(tServiceResponseObject);
    }

    public void fireResult(JSONResponse<T> response) {
        if (callback != null) {
            if (checkSuccess(response)) {
                callback.onSuccess(response.getResult());
            } else if (response.getError() != null) {
                callback.onServerError(response.getError());
            } else {
                callback.onFailure(response.getFail());
            }
        }
    }

    /**
     * Check {@link JSONResponse<T>}
     *
     * @param result {@link JSONResponse<T>}
     * @return Return true, if {@link JSONResponse<T>#getEntity()} not null, false otherwise.
     */
    protected boolean checkSuccess(JSONResponse<T> result) {
        return result != null && result.getResult() != null;
    }

    public void fireStart() {
        if (callback != null) {
            callback.onStartTask();
        }
    }

    public void fireFinish() {
        if (callback != null) {
            callback.onFinishTask();
        }
    }
}