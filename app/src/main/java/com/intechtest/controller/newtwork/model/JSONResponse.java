package com.intechtest.controller.newtwork.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Server Kurtnebiev on 02.08.2016.
 * Copyright (c) 2016 intech
 */
public class JSONResponse<T> {

    @JsonProperty("error")
    private Error error;

    @JsonProperty("results")
    private T result;

    private Fail fail;


    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Fail getFail() {
        return fail;
    }

    public void setFail(Fail fail) {
        this.fail = fail;
    }
}
