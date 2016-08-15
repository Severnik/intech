package com.intechtest.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Server Kurtnebiev on 09.07.2016.
 * Copyright (c) 2016 intech
 */
public class PhotoGridItem extends LinearLayout {
    public PhotoGridItem(Context context) {
        super(context);
    }

    public PhotoGridItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PhotoGridItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
