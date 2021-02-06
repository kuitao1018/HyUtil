package com.hongyan.hyutil.utils;

import android.util.Log;
import android.widget.Toast;

import com.hongyan.hyutil.BaseApplication;
import com.simple.spiderman.SpiderMan;

/**
 * author: hongyan
 * created on: 2019/4/1 16:15
 * description:
 */
public class T {

    private static Toast toast;
    private static String TAG = "ToastUtils";


    public static void show(int resId) {
        show(BaseApplication.getContext().getString(resId));
    }


    public static void show(String text) {
        try {
            if (toast != null) {
                toast.cancel();
                toast = Toast.makeText(BaseApplication.getContext(), text, Toast.LENGTH_SHORT);
            } else {
                toast = Toast.makeText(BaseApplication.getContext(), text, Toast.LENGTH_SHORT);
            }
            toast.show();
        } catch (Exception e) {
            SpiderMan.show(e);
            Log.e(TAG, e.getMessage());
        }
    }

}
