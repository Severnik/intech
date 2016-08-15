package com.intechtest;

import android.app.Application;

/**
 * Created by Server Kurtnebiev on 09.07.2016.
 * Copyright (c) 2016 intech
 */
public class TheApplication extends Application {
    private static TheApplication sApplication;

    public static TheApplication getInstance() {
        return sApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }
}
