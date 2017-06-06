package com.ariets.kata.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.StyleableRes;
import android.util.AttributeSet;

public class UiUtils {

    public static TypedArray obtainStyledAttributes(Context context, AttributeSet attrs, @StyleableRes int[] attributeRes) {
        return context.getTheme().obtainStyledAttributes(attrs, attributeRes, 0, 0);
    }

}
