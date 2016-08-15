package com.intechtest.controller.newtwork.event;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intechtest.controller.newtwork.model.JSONResponse;

/**
 * Created by Server Kurtnebiev on 02.08.2016.
 * Copyright (c) 2016 intech
 */
public abstract class AbstractService<T> {

    protected JSONResponse<T> serviceResponseObject;

    protected static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    public JSONResponse<T> getServiceResponseObject() {
        return serviceResponseObject;
    }

    public abstract void run();

}
