package com.hongyan.hyutil.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.hongyan.hyutil.R;

/**
 * @CreateDate 2018/9/10 15:24
 * @Author by  hongyan
 * @Class 标题栏
 */

public class CustomToolBar extends LinearLayout {
    private String leftTvText;
    private String rightTvText;
    private String titleText;
    private LinearLayout mLeftBtn;
    private LinearLayout mRightBtn;
    private TextView mTitleTv;
    private TextView mRightTv;

    public CustomToolBar(Context context) {
        this(context, null);
    }

    public CustomToolBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    /**
     * 初始化属性
     *
     * @param attrs
     */
    public void initView(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomToolBar);
/**-------------获取左边按钮属性------------*/
        Boolean isLeftBtnVisible = typedArray.getBoolean(R.styleable.CustomToolBar_left_btn_visible, false);
        int leftResId = typedArray.getResourceId(R.styleable.CustomToolBar_left_btn_src, -1);
/**-------------获取左边文本属性------------*/
        Boolean isLeftTvVisible = typedArray.getBoolean(R.styleable.CustomToolBar_left_tv_visible, false);
        if (typedArray.hasValue(R.styleable.CustomToolBar_left_tv_text)) {
            leftTvText = typedArray.getString(R.styleable.CustomToolBar_left_tv_text);
        }
/**-------------获取右边按钮属性------------*/
        Boolean isRightBtnVisible = typedArray.getBoolean(R.styleable.CustomToolBar_right_btn_visible, false);
        int rightResId = typedArray.getResourceId(R.styleable.CustomToolBar_right_btn_src, -1);
/**-------------获取右边文本属性------------*/
        Boolean isRightTvVisible = typedArray.getBoolean(R.styleable.CustomToolBar_right_tv_visible, false);
        if (typedArray.hasValue(R.styleable.CustomToolBar_right_tv_text)) {
            rightTvText = typedArray.getString(R.styleable.CustomToolBar_right_tv_text);
        }
/**-------------获取标题属性------------*/
        Boolean isTitleVisible = typedArray.getBoolean(R.styleable.CustomToolBar_title_visible, false);
        if (typedArray.hasValue(R.styleable.CustomToolBar_title_text)) {
            titleText = typedArray.getString(R.styleable.CustomToolBar_title_text);
        }
/**-------------背景颜色------------*/
        int backgroundResId = typedArray.getResourceId(R.styleable.CustomToolBar_barBackground, -1);
        typedArray.recycle();
/**-------------设置内容------------*/
        View barLayoutView = View.inflate(getContext(), R.layout.view_common_toolbar, null);
        mLeftBtn = (LinearLayout) barLayoutView.findViewById(R.id.toolbar_left_btn);
        TextView leftTv = (TextView) barLayoutView.findViewById(R.id.toolbar_left_tv);
        mTitleTv = (TextView) barLayoutView.findViewById(R.id.toolbar_title_tv);
        mRightBtn = (LinearLayout) barLayoutView.findViewById(R.id.toolbar_right_btn);
        mRightTv = (TextView) barLayoutView.findViewById(R.id.toolbar_right_tv);
        ConstraintLayout barRlyt = (ConstraintLayout) barLayoutView.findViewById(R.id.toolbar_content_rlyt);
        if (isLeftBtnVisible) {
            mLeftBtn.setVisibility(VISIBLE);
        }
        if (isLeftTvVisible) {
            leftTv.setVisibility(VISIBLE);
        }
        if (isRightBtnVisible) {
            mRightBtn.setVisibility(VISIBLE);
        }
        if (isRightTvVisible) {
            mRightTv.setVisibility(VISIBLE);
        }
        if (isTitleVisible) {
            mTitleTv.setVisibility(VISIBLE);
        }
        leftTv.setText(leftTvText);
        mRightTv.setText(rightTvText);
        mTitleTv.setText(titleText);
        if (leftResId != -1) {
            mLeftBtn.setBackgroundResource(leftResId);
        }
        if (rightResId != -1) {
            mRightBtn.setBackgroundResource(rightResId);
        }
        if (backgroundResId != -1) {
            barRlyt.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color_blue));
        }
        //将设置完成之后的View添加到此LinearLayout中
        addView(barLayoutView, 0);
    }

    /**
     * 给左边按键设置点击事件
     */
    public void setLeftBtListener(OnClickListener v) {
        mLeftBtn.setOnClickListener(v);
    }

    /**
     * 给右边按键设置点击事件
     */
    public void setRightBtListener(OnClickListener v) {
        mRightBtn.setOnClickListener(v);
    }

    /**
     * 给右边文字按键设置点击事件
     */
    public void setRightTvListener(OnClickListener v) {
        mRightTv.setOnClickListener(v);
    }

    /**
     * 设置标题栏
     */
    public void setCustomTitleText(String text) {
        if (text != null) {
            mTitleTv.setText(text);
        }
    }

    /**
     * 设置标题栏字体大小
     */
    public void setCustomTitleTextSize(int i) {
        if (0 != i) {
            mTitleTv.setTextSize(i);
        }
    }

    /**
     * 给右边文字
     */
    public void setRightTvText(String text) {
        if (text != null) {
            mRightTv.setText(text);
        }
    }
}
