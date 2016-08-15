package com.intechtest.controller.newtwork.event;

import com.intechtest.controller.newtwork.model.Fail;
import com.intechtest.controller.newtwork.model.Error;

/**
 * Created by Server Kurtnebiev on 02.08.2016.
 * Copyright (c) 2016 intech
 */
public interface IRemoteServiceCallback<E> {
    /**
     * Start remote call process
     */
    void onStartTask();

    void onSuccess(E result);

    void onFailure(Fail error);

    void onServerError(Error error);

    /**
     * End remote call process, call in any case after onStart,onSuccess,onFailure,onServerError
     */
    void onFinishTask();

}
