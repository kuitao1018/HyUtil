package com.hongyan.hyutil.utils;

import android.os.CountDownTimer;

public class CountDownUtil {

    private CountDownTimer mTimer;

    private int interval = 1000;

    public CountDownUtil() {
    }

    /**
     * 日期转时间戳
     *
     * @param date
     * @return
     */
    public static long getTimeStamp(String date) {
        return DateUtil.str2Date(date).getTime();
    }

    /**
     * 开始倒计时
     *
     * @param startTime      开始时间（时间戳）
     * @param minuteInterval 时间间隔（单位：分）
     * @param callBack
     */
    public void start(long startTime, int minuteInterval, OnCountDownCallBack callBack) {
        long lengthTime = minuteInterval * 60 * interval;
        //查看是否为毫秒的时间戳
        boolean isMillSecond = (String.valueOf(startTime).length() == 13);
        startTime = startTime * (isMillSecond ? 1 : interval);
        long endTime = startTime + lengthTime;
        long curTime = System.currentTimeMillis();
        mTimer = getTimer(endTime - curTime, interval, callBack);
        if (Math.abs(curTime - startTime) > lengthTime) {
            if (callBack != null) {
                callBack.onFinish();
            }
        } else {
            mTimer.start();
        }
    }

    public CountDownTimer getTimer(long millisInFuture, final long interval, final OnCountDownCallBack callBack) {
        return new CountDownTimer(millisInFuture, interval) {
            @Override
            public void onTick(long millisUntilFinished) {
                int day = 0;
                int hour = 0;
                int minute = (int) (millisUntilFinished / interval / 60);
                int second = (int) (millisUntilFinished / interval % 60);
                if (minute > 60) {
                    hour = minute / 60;
                    minute = minute % 60;
                }
                if (hour > 24) {
                    day = hour / 24;
                    hour = hour % 24;
                }
                if (callBack != null) {
                    callBack.onProcess(day, hour, minute, second);
                }
            }

            @Override
            public void onFinish() {
                if (callBack != null) {
                    callBack.onFinish();
                }
            }
        };
    }

    /**
     * 开始倒计时
     *
     * @param endTime  结束时间（时间戳）
     * @param callBack
     */
    public void start(long endTime, OnCountDownCallBack callBack) {
        long curTime = System.currentTimeMillis();
        mTimer = getTimer(endTime - curTime, interval, callBack);
        if (endTime <= curTime) {
            if (callBack != null) {
                callBack.onFinish();
            }
        } else {
            mTimer.start();
        }
    }

    public void startCountDown(long countdown, OnCountDownCallBack callBack) {
        mTimer = getTimer(countdown , interval, callBack);
        if (countdown <= 0) {
            if (callBack != null) {
                callBack.onFinish();
            }
        } else {
            mTimer.start();
        }
    }

    /**
     * 开始倒计时
     *
     * @param startTime 开始时间（时间戳）
     * @param endTime   结束时间（时间戳）
     * @param callBack
     */
    public void start(long startTime, long endTime, OnCountDownCallBack callBack) {
        mTimer = getTimer(endTime - startTime, interval, callBack);
        if (endTime <= startTime) {
            if (callBack != null) {
                callBack.onFinish();
            }
        } else {
            mTimer.start();
        }
    }

    /**
     * 必用
     */
    public void onDestroy() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    public interface OnCountDownCallBack {

        void onProcess(int day, int hour, int minute, int second);

        void onFinish();
    }
}