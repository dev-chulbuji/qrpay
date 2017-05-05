package com.jitosoft.qrpay.presentation.util;

import android.util.Log;

import com.jitosoft.qrpay.BuildConfig;

/**
 * Created by jihoon on 2017. 4. 28..
 */

public class LogUtils {

    private static final boolean isDebug = BuildConfig.DEBUG;

    public static void debug(String tag, String msg) {
        if (isDebug) {
            Log.d(tag, msg);
        }
    }

    public static void info(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void error(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg);
        }
    }
}
