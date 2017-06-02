package com.ariets.kata.model;

import java.text.DecimalFormat;

public class Formatter {

    public static String formatDouble(double value) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(value);
    }

}
