package com.intechtest.controller.error;

import android.app.Dialog;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;


import com.intechtest.R;
import com.intechtest.controller.error.event.ErrorHandler;
import com.intechtest.controller.error.event.ErrorHandlerListener;
import com.intechtest.controller.newtwork.model.Fail;
import com.intechtest.controller.newtwork.model.Error;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Server Kurtnebiev on 02.08.2016.
 * Copyright (c) 2016 intech
 */
public abstract class AbstractErrorHandler implements ErrorHandler {
    protected FragmentActivity activity;
    protected Dialog alertDialog;
    protected TextView txtBody;
    protected Set<ErrorHandlerListener> handlerListeners = new HashSet<>();

    protected AbstractErrorHandler(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void handleError(Error communicationError) {
        manageLoadDialog(false);
        ErrorCode result = ErrorCode.getErrorCode(communicationError);
        if (result != null) {
            result.response(this, activity);
        } else {
            showDialog("Error", communicationError.getMessage());
        }
    }

    public void handleFail(Fail fail) {
        if (fail.getMessage().equals(Fail.NETWORK_ERROR)) {
            showDialog(activity.getString(R.string.title_error_dialog),
                    activity.getString(R.string.no_internet_connection), null, true);
        }
    }

    @Override
    public void manageLoadDialog(boolean show) {
        manageLoadDialog(show, null);
    }

    public void showDialog(String title, String message) {
        showDialog(title, message, null, false);
    }

    public void showDialog(String title, String message, final Dialog.OnClickListener listener, boolean btnOrange) {
        if (alertDialog == null) {
            alertDialog = new Dialog(activity);
            alertDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            View view = LayoutInflater.from(activity).inflate(R.layout.login_alert_popup, null);
            view.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(alertDialog, v.getId());
                    }
                    alertDialog.dismiss();
                }
            });

            alertDialog.setContentView(view);
            alertDialog.setCancelable(true);
            txtBody = (TextView) view.findViewById(R.id.txt_body);
            ((TextView) view.findViewById(R.id.title)).setText(title);
        }
        txtBody.setText(message != null ? message : "");
        if (!alertDialog.isShowing()) {
            alertDialog.show();
        }
    }

    public void hideDialog() {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    @Override
    public void addListener(ErrorHandlerListener errorHandlerListener) {
        handlerListeners.add(errorHandlerListener);
    }

    @Override
    public void removeListener(ErrorHandlerListener errorHandlerListener) {
        handlerListeners.remove(errorHandlerListener);
    }

    protected void fireProgressDialogShow(boolean show) {
        for (ErrorHandlerListener handlerListener : handlerListeners) {
            handlerListener.progressDialogShow(show);
        }
    }
}
