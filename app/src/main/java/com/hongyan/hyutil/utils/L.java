package com.hongyan.hyutil.utils;

import android.util.Log;

/**
 * class log 日志工具类
 */

public class L {
    // 日志过长,分段打印日志
    public static void e(String tag, String msg) {
//        if (BuildConfig.DEBUG) {
        int strLength = msg.length();
        int start = 0;
        int logmaxlength = 2000;     //规定每段显示的长度
        int end = logmaxlength;
        for (int i = 0; i < 100; i++) {
            //剩下的文本还是大于规定长度则继续重复截取并输出
            if (strLength > end) {
                Log.e(tag + i, msg.substring(start, end));
                start = end;
                end = end + logmaxlength;
            } else {
                Log.e(tag, msg.substring(start, strLength));
                break;
            }
        }
//        }
    }

    public static void d(String msg) {
        Log.d("", msg);
    }

    public static void d(String tag, String msg) {
        Log.d(tag, msg);
    }
}

