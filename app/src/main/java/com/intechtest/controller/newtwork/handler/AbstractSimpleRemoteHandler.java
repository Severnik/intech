package com.intechtest.controller.newtwork.handler;

import com.intechtest.controller.newtwork.event.AbstractService;
import com.intechtest.controller.newtwork.event.IRemoteServiceCallback;
import com.intechtest.controller.newtwork.model.JSONResponse;

/**
 * Created by Server Kurtnebiev on 02.08.2016.
 * Copyright (c) 2016 intech
 */
public class AbstractSimpleRemoteHandler<T> extends AbstractRemoteHandler<T> {

    public AbstractSimpleRemoteHandler(IRemoteServiceCallback<T> callback) {
        super(callback);
    }

    public <V> JSONResponse<V> executeService(AbstractService<V> abstractRPCService) {
        abstractRPCService.run();
        return abstractRPCService.getServiceResponseObject();
    }

}