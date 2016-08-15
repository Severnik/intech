package com.intechtest.controller.error.event;

import android.app.Dialog;

import com.intechtest.controller.newtwork.model.Error;
import com.intechtest.controller.newtwork.model.Fail;


/**
 * Created by Server Kurtnebiev on 02.08.2016.
 * Copyright (c) 2016 intech
 */
public interface ErrorHandler {

    void handleFail(Fail fail);

    void handleError(Error communicationError);

    void manageLoadDialog(boolean show);

    void manageLoadDialog(boolean show, String message);

    void showDialog(String title, String message);

    void showDialog(String title, String message, Dialog.OnClickListener listener, boolean btnOrange);

    void addListener(ErrorHandlerListener errorHandlerListener);

    void removeListener(ErrorHandlerListener errorHandlerListener);
}
