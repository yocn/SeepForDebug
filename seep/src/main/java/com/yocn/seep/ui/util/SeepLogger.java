package com.yocn.seep.ui.util;

import android.util.Log;

public class SeepLogger {
    public static String TAG = "SeepLogger";

    public static void d(String msg) {
        dt(TAG, msg);
    }

    public static void dt(String tag, String msg) {
        Log.d(tag, msg);
    }
}
