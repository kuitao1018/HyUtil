package com.hongyan.hyutil.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;


/**
 * @author: hongyan
 * @created on: 2019/5/13 17:11
 * @description: 去掉RecyclerView 最后一条的底部分割线
 */
public class DividerItemDecorationUtils extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = new int[]{16843284};
    private Drawable mDivider;
    private int mOrientation;
    private Context mContext;

    /**
     * 分隔线方法一
     *
     * @param context     上下文
     * @param orientation DividerItemDecoration.VERTICAL
     */
    public DividerItemDecorationUtils(Context context, int orientation) {
        this.mContext = context;
        TypedArray a = context.obtainStyledAttributes(ATTRS);
        this.mDivider = a.getDrawable(0);
        a.recycle();
        this.setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if (orientation != 0 && orientation != 1) {
            throw new IllegalArgumentException("invalid orientation");
        } else {
            this.mOrientation = orientation;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        if (this.mOrientation == 1) {
            this.drawVertical(c, parent);
        } else {
            this.drawHorizontal(c, parent);
        }

    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        //遍历去除最后一条的分割线
        for (int i = 0; i < childCount - 1; ++i) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + this.mDivider.getIntrinsicHeight();
            this.mDivider.setBounds(left, top, right, bottom);
            this.mDivider.draw(c);
        }

    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; ++i) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + params.rightMargin;
            int right = left + this.mDivider.getIntrinsicHeight();
            this.mDivider.setBounds(left, top, right, bottom);
            this.mDivider.draw(c);
        }

    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        if (this.mOrientation == 1) {
            outRect.set(0, 0, 0, this.mDivider.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, this.mDivider.getIntrinsicWidth(), 0);
        }

    }


    /**
     * 分隔线方法二
     *
     * @param context    上下文
     * @param drawableId 资源文件
     */

    public static RecyclerView.ItemDecoration getDividerItemDecoration(Context context, int drawableId) {
        if (drawableId == 0) {
            return null;
        }
        DividerItemDecoration itemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(context, drawableId));
        return itemDecoration;
    }


}
