package com.intechtest.ui.event;

/**
 * Created by Server Kurtnebiev on 10.07.2016.
 * Copyright (c) 2016 intech
 */
public interface OnItemClick<T> {
    void onClick(T item, int position);
}
