package com.ariets.kata.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class KeyboardUtils {

    public static void removeKeyboard(@NonNull View focusView) {
        InputMethodManager imm = (InputMethodManager) focusView.getContext()
                .getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
    }

}
