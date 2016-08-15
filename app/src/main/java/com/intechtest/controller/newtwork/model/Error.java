package com.intechtest.controller.newtwork.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Server Kurtnebiev on 02.08.2016.
 * Copyright (c) 2016 intech
 */
public class Error {

    @JsonProperty("code")
    private int code;

    @JsonProperty("message")
    private String message;
    
    public Error() {

    }

    public Error(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
