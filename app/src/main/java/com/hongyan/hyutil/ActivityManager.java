package com.hongyan.hyutil;

import android.app.Activity;

import java.util.Stack;

public class ActivityManager {
    private static Stack<Activity> activities;

    private static class ActivityManagerHolder {
        private static final ActivityManager INSTANCE = new ActivityManager();
    }

    public static ActivityManager getInstance() {
        return ActivityManagerHolder.INSTANCE;
    }

    /**
     * 入栈
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (activities == null) {
            activities = new Stack<>();
        }
        if (activities.contains(activity)) {
            activities.remove(activity);
        }
        activities.add(activity);
    }

    /**
     * 出栈
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (activities == null) {
            activities = new Stack<>();
        }

        if (activities.isEmpty()) {
            return;
        }
        if (activity != null) {
            activities.remove(activity);
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 获取当前的 activity
     *
     * @return act
     */
    public Activity currentActivity() {
        if (activities == null || activities.isEmpty()) {
            return null;
        }
        return activities.lastElement();
    }

    public void popActivity(Activity activity) {
        if (activities == null) {
            activities = new Stack<>();
        }
        if (activities.isEmpty()) {
            return;
        }
        if (activity != null) {
            activities.remove(activity);
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    public void popActivity(Class<?> cls) {
        popActivity();
        if (isCurrentActivity(cls)) {
            popActivity();
        }
    }

    /**
     * 根据类名判断是否是当前activity
     */
    private boolean isCurrentActivity(Class<?> cls) {
        Activity currentActivity = currentActivity();
        if (null == currentActivity) {
            return false;
        }

        if (currentActivity.getClass().equals(cls)) {
            return true;
        }
        return false;
    }

    private void popActivity() {
        if (activities == null) {
            activities = new Stack<>();
            return;
        }

        if (activities.isEmpty()) {
            return;
        }

        Activity activity = activities.pop();
        if (activity != null) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 全部出栈
     */
    public void exit() {
        while (true) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            removeActivity(activity);
        }
    }

}
