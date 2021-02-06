package com.hongyan.hyutil.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.hongyan.hyutil.R;
import com.hongyan.hyutil.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * *******************************
 *
 * @Created by Android Studio.
 * @Author: hongyan.tao
 * @Date: 2019/12/27  13:32
 * @Description 这个类的用途
 * ******************************
 */
public class CustomWheelView extends ScrollView {
    public static final String TAG = CustomWheelView.class.getSimpleName();


    private Context context;
    private LinearLayout views;
    private List<String> items;
    private static final int OFF_SET_DEFAULT = 1;
    // 偏移量（需要在最前面和最后面补全）
    private int offset = OFF_SET_DEFAULT;
    private int initialY;
    private Runnable scrollerTask;
    private int newCheck = 50;
    private Paint paint;
    private int viewWidth;
    // 每页显示的数量
    private int displayItemCount;
    private int selectedIndex = 1;
    private int[] selectedAreaBorder;
    private int itemHeight = 0;

    public interface OnWheelViewListener {
        void onSelected(int selectedIndex, String item);
    }

    public CustomWheelView(Context context) {
        super(context);
        init(context);
    }

    public CustomWheelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomWheelView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }


    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    private List<String> getItems() {
        return items;
    }

    public void setItems(List<String> list) {
        if (null == items) {
            items = new ArrayList<String>();
        }
        items.clear();
        items.addAll(list);
        // 前面和后面补全
        for (int i = 0; i < offset; i++) {
            items.add(0, "");
            items.add("");
        }
        initData();

    }


    private void init(Context context) {
        this.context = context;
        this.setVerticalScrollBarEnabled(false);
        views = new LinearLayout(context);
        views.setOrientation(LinearLayout.VERTICAL);
        this.addView(views);

        scrollerTask = new Runnable() {
            @Override
            public void run() {

                int newY = getScrollY();
                if (initialY - newY == 0) { // stopped
                    final int remainder = initialY % itemHeight;
                    final int divided = initialY / itemHeight;
                    if (remainder == 0) {
                        selectedIndex = divided + offset;
                        onSelectCallBack();
                    } else {
                        if (remainder > itemHeight / 2) {
                            CustomWheelView.this.post(new Runnable() {
                                @Override
                                public void run() {
                                    CustomWheelView.this.smoothScrollTo(0, initialY - remainder + itemHeight);
                                    selectedIndex = divided + offset + 1;
                                    onSelectCallBack();
                                }
                            });
                        } else {
                            CustomWheelView.this.post(new Runnable() {
                                @Override
                                public void run() {
                                    CustomWheelView.this.smoothScrollTo(0, initialY - remainder);
                                    selectedIndex = divided + offset;
                                    onSelectCallBack();
                                }
                            });
                        }


                    }


                } else {
                    initialY = getScrollY();
                    CustomWheelView.this.postDelayed(scrollerTask, newCheck);
                }
            }
        };


    }


    public void startScrollerTask() {
        initialY = getScrollY();
        this.postDelayed(scrollerTask, newCheck);
    }

    private void initData() {
        displayItemCount = offset * 2 + 1;
        for (String item : items) {
            views.addView(createView(item));
        }
        refreshItemView(0);
    }

    /**
     * 这里可以设置字体的属性 大小啦,边距啦,什么东西都可以在这里设置;
     */
    private TextView createView(String item) {
        TextView tv = new TextView(context);
        tv.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tv.setSingleLine(true);
        // 设置字体大小为20sp
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        // 设置文字内容
        tv.setText(item);
        // 设置文字居中
        tv.setGravity(Gravity.CENTER);
        // 设置内边距
        int padding = dip2px(8);
        tv.setPadding(padding, padding, padding, padding);
        if (0 == itemHeight) {
            itemHeight = getViewMeasuredHeight(tv);
            views.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeight * displayItemCount));
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) this.getLayoutParams();
            this.setLayoutParams(new LinearLayout.LayoutParams(lp.width, itemHeight * displayItemCount));
        }
        return tv;
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        refreshItemView(t);
        if (t > oldt) {
            L.d(TAG, "向下滚动");
        } else {
            L.d(TAG, "向上滚动");

        }
    }

    private void refreshItemView(int y) {
        int position = y / itemHeight + offset;
        int remainder = y % itemHeight;
        int divided = y / itemHeight;
        if (remainder == 0) {
            position = divided + offset;
        } else {
            if (remainder > itemHeight / 2) {
                position = divided + offset + 1;
            }
        }

        int childSize = views.getChildCount();
        for (int i = 0; i < childSize; i++) {
            TextView itemView = (TextView) views.getChildAt(i);
            if (null == itemView) {
                return;
            }
            if (position == i) {
                itemView.setTextColor(ContextCompat.getColor(context, R.color.color_black));
            } else {
                itemView.setTextColor(ContextCompat.getColor(context, R.color.color_gray));
            }
        }
    }

    /**
     * 获取选中区域的边界
     */
    private int[] obtainSelectedAreaBorder() {
        if (null == selectedAreaBorder) {
            selectedAreaBorder = new int[2];
            selectedAreaBorder[0] = itemHeight * offset;
            selectedAreaBorder[1] = itemHeight * (offset + 1);
        }
        return selectedAreaBorder;
    }


    @Override
    public void setBackgroundDrawable(Drawable background) {

        if (viewWidth == 0) {
            viewWidth = ((Activity) context).getWindowManager().getDefaultDisplay().getWidth();
        }

        if (null == paint) {
            paint = new Paint();
            paint.setColor(ContextCompat.getColor(context, R.color.color_blue));
            paint.setStrokeWidth(dip2px(1f));
        }

        background = new Drawable() {
            @Override
            public void draw(Canvas canvas) {
                canvas.drawLine(viewWidth * 1 / 10, obtainSelectedAreaBorder()[0], viewWidth * 9 / 10, obtainSelectedAreaBorder()[0], paint);
                canvas.drawLine(viewWidth * 1 / 10, obtainSelectedAreaBorder()[1], viewWidth * 9 / 10, obtainSelectedAreaBorder()[1], paint);
            }

            @Override
            public void setAlpha(int alpha) {

            }

            @Override
            public void setColorFilter(ColorFilter cf) {

            }

            @Override
            public int getOpacity() {
                return PixelFormat.UNKNOWN;
            }
        };
        super.setBackgroundDrawable(background);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        setBackgroundDrawable(null);
    }

    /**
     * 选中回调
     */
    private void onSelectCallBack() {
        if (null != onWheelViewListener) {
            onWheelViewListener.onSelected(selectedIndex, items.get(selectedIndex));
        }

    }

    public void setSelectPosition(int position) {
        final int p = position;
        selectedIndex = p + offset;
        this.post(new Runnable() {
            @Override
            public void run() {
                CustomWheelView.this.smoothScrollTo(0, p * itemHeight);
            }
        });

    }

    public String getSelectItem() {
        return items.get(selectedIndex);
    }

    public int getSelectIndex() {
        return selectedIndex - offset;
    }


    @Override
    public void fling(int velocityY) {
        super.fling(velocityY / 3);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {

            startScrollerTask();
        }
        return super.onTouchEvent(ev);
    }


    private OnWheelViewListener onWheelViewListener;

    public OnWheelViewListener getOnWheelViewListener() {
        return onWheelViewListener;
    }

    public void setOnWheelViewListener(OnWheelViewListener onWheelViewListener) {
        this.onWheelViewListener = onWheelViewListener;
    }

    private int dip2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private int getViewMeasuredHeight(View view) {
        int width = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        view.measure(width, expandSpec);
        return view.getMeasuredHeight();
    }
}
