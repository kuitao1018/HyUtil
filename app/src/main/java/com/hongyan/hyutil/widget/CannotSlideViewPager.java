package com.hongyan.hyutil.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * @author: hongyan
 * @created on: 2019/5/10 14:56
 * @description: 不可滑动的viewPager
 */
public class CannotSlideViewPager extends ViewPager {

    private boolean isCanScroll = true;

    public CannotSlideViewPager(Context context) {
        super(context);
    }

    public CannotSlideViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScanScroll(boolean isCanScroll) {

        this.isCanScroll = isCanScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return false;
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
