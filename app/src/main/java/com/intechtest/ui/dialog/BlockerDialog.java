package com.intechtest.ui.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import com.intechtest.R;

/**
 * Created by Server Kurtnebiev on 02.08.2016.
 * Copyright (c) 2016 intech
 */
public class BlockerDialog extends ProgressDialog {

    public BlockerDialog(Context context) {
        super(context);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    @Override
    public void show() {
        if (!this.isShowing()) {
            super.show();
            setContentView(R.layout.dialog_progress_layout);
        }
    }
}