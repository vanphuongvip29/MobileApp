package com.sinhvien.tourapp;

import java.text.NumberFormat;
import java.util.Locale;

public class MyUtils {
    public static String formatDouble(double value) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());
        return numberFormat.format(value);
    }
}
