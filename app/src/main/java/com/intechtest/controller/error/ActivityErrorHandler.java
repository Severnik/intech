package com.intechtest.controller.error;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;

import com.intechtest.ui.dialog.BlockerDialog;
import com.intechtest.controller.newtwork.model.Fail;
import com.intechtest.controller.newtwork.model.Error;


/**
 * Created by Server Kurtnebiev on 02.08.2016.
 * Copyright (c) 2016 intech
 */
public class ActivityErrorHandler extends AbstractErrorHandler {

    private BlockerDialog blockerDialog;

    public ActivityErrorHandler(FragmentActivity activity) {
        super(activity);
    }

    @Override
    public void handleError(Error error) {
        super.handleError(error);
    }

    @Override
    public void handleFail(Fail fail) {
        super.handleFail(fail);
    }

    @Override
    public void manageLoadDialog(boolean show, String message) {
        if (blockerDialog == null) {
            blockerDialog = new BlockerDialog(activity);
            blockerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        if (show && !blockerDialog.isShowing()) {
            fireProgressDialogShow(true);
            blockerDialog.show();
        }
        if (!show && blockerDialog.isShowing()) {
            blockerDialog.dismiss();
            fireProgressDialogShow(false);
        }
    }
}
