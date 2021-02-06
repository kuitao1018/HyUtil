package com.hongyan.hyutil.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.hongyan.hyutil.BaseApplication;
import com.simple.spiderman.SpiderMan;

public class SpUtils {
    private final String USER_PHONE = "user_phone";
    private final String USER_ICONLIST = "user_iconlist";
    private static final String PREFERENCE_NAME = "preference_name";
    private static SharedPreferences mSharedPreferences;

    private SpUtils() {
        mSharedPreferences = BaseApplication.getContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    private static class SpUtilsHolder {
        private static final SpUtils instance = new SpUtils();
    }

    public static SpUtils getInstance() {
        return SpUtilsHolder.instance;
    }

    public boolean setString(String keyName, String keyValue) {
        try {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString(keyName, keyValue);
            editor.commit();
        } catch (Exception ex) {
            SpiderMan.show(ex);
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean setBoolean(String keyvalue, boolean flag) {
        try {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putBoolean(keyvalue, flag);
            editor.commit();
        } catch (Exception ex) {
            SpiderMan.show(ex);
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public String getString(String key) {
        return mSharedPreferences.getString(key, "");
    }

    public boolean getBoolean(String key) {
        return mSharedPreferences.getBoolean(key, false);
    }

    public String getUser() {
        return mSharedPreferences.getString(USER_PHONE, "");
    }
}
