package com.intechtest.model;

import android.os.Bundle;

import java.io.Serializable;

/**
 * Created by Server Kurtnebiev on 02.08.2016.
 * Copyright (c) 2016 intech
 */
public abstract class AbstractModel implements Serializable {

    public AbstractModel(AbstractModel abstractModel) {
    }

    protected AbstractModel() {
    }

    public Bundle toBundle() {
        Bundle b = new Bundle();
        b.putSerializable(getBundleKey(), this);
        return b;
    }

    protected abstract String getBundleKey();
}
