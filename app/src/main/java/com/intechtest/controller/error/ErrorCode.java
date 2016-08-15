package com.intechtest.controller.error;

import android.support.v4.app.FragmentActivity;

import com.intechtest.controller.error.event.ErrorHandler;
import com.intechtest.controller.newtwork.model.Error;


/**
 * Created by Server Kurtnebiev on 02.08.2016.
 * Copyright (c) 2016 intech
 */
public enum ErrorCode {

    SESSION_IS_ACTIVE(401, "Session for current user is active");


    private int code;
    private String message;

    private ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public void response(ErrorHandler errorHandler, FragmentActivity activity) {
        errorHandler.showDialog("Error", message);
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }


    public static ErrorCode getErrorCode(Error error) {
        ErrorCode result = null;
        for (ErrorCode errorCode : ErrorCode.values()) {
            if (errorCode.getCode() == error.getCode() &&
                    errorCode.getMessage().equals(error.getMessage())) {
                result = errorCode;
                break;
            }
        }
        return result;
    }


}
