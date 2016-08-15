package com.intechtest.controller.newtwork.model;

/**
 * Created by Server Kurtnebiev on 02.08.2016.
 * Copyright (c) 2016 intech
 */
public class Fail {

    public static final String NETWORK_ERROR = "NETWORK_ERROR";

    private String message;

    private Exception exception;

    public Fail(String message, Exception e) {
        this.message = message;
        this.exception = e;
    }

    public Fail(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Exception getException() {
        return exception;
    }
}
