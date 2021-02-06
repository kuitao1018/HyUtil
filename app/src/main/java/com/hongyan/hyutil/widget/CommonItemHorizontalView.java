package com.hongyan.hyutil.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hongyan.hyutil.R;

import java.math.BigDecimal;

/**
 * 通用的数据采集View
 * 1、展示图标，扫一扫或者弹窗图标
 * 2、弹窗item，使用TextView
 * 3、不弹窗item使用EditText  横向
 */
public class CommonItemHorizontalView extends LinearLayout {

    private TextView tvContent, tvTitle;
    private EditText etContent;
    private LinearLayout llRightImg;
    private ImageView ivRightImg;

    //是否可以编辑
    private Boolean isEditor;
    private OnSysClickListener onSysClickListener;

    public interface OnSysClickListener {
        void Sys();
    }

    public void setOnSysClickListener(OnSysClickListener onSysClickListener) {
        this.onSysClickListener = onSysClickListener;
    }

    public CommonItemHorizontalView(Context context) {
        this(context, null);
    }

    public CommonItemHorizontalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.common_item_horizontal_view, this, true);
        TextView tvNotNull = findViewById(R.id.tvNotNull);
        tvTitle = findViewById(R.id.tvTitle);
        tvContent = findViewById(R.id.tvContent);
        etContent = findViewById(R.id.etContent);
        llRightImg = findViewById(R.id.ll_right_Img);
        ivRightImg = findViewById(R.id.iv_right_Img);
        View viewLine = findViewById(R.id.view_line);

        @SuppressLint("CustomViewStyleable")
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CommonItemHorizontalView);
        //是否是必填项
        if (ta.getBoolean(R.styleable.CommonItemHorizontalView_isNotNull, false)) {
            tvNotNull.setVisibility(VISIBLE);
        } else {
            tvNotNull.setVisibility(GONE);
        }
        //是否可以编辑
        isEditor = ta.getBoolean(R.styleable.CommonItemHorizontalView_isEditor, false);
        if (isEditor) {
            tvContent.setVisibility(GONE);
            etContent.setVisibility(VISIBLE);
        } else {
            tvContent.setVisibility(VISIBLE);
            etContent.setVisibility(GONE);
        }
        //是否展示右侧Dialog
        if (ta.getBoolean(R.styleable.CommonItemHorizontalView_isRightDialog, false)) {
            llRightImg.setVisibility(VISIBLE);
            ivRightImg.setImageResource(R.drawable.arrow_right_black_24dp);
        } else {
            llRightImg.setVisibility(INVISIBLE);
        }
        //标题内容
        String tvTitleContent = ta.getString(R.styleable.CommonItemHorizontalView_tvTitleContent);
        if (!TextUtils.isEmpty(tvTitleContent)) {
            tvTitle.setText(tvTitleContent);
        }
        //提示内容
        String tvHintContent = ta.getString(R.styleable.CommonItemHorizontalView_tvHintContent);
        if (!TextUtils.isEmpty(tvHintContent)) {
            if (tvContent.getVisibility() == VISIBLE) {
                tvContent.setHint(tvHintContent);
            }
            if (etContent.getVisibility() == VISIBLE) {
                etContent.setHint(tvHintContent);
            }
        }
        //是否显示下划线
        if (ta.getBoolean(R.styleable.CommonItemHorizontalView_isShowBottomLine, false)) {
            viewLine.setVisibility(VISIBLE);
        } else {
            viewLine.setVisibility(GONE);
        }
        ta.recycle();

    }

    //设置内容
    public void setContent(String content) {
        if (TextUtils.isEmpty(content)) {
            tvContent.setText("");
            etContent.setText("");
            return;
        }
        tvContent.setText(content);
        etContent.setText(content);
    }

    //获取内容
    public String getContent() {
        String content = "";
        if (tvContent.getVisibility() == VISIBLE) {
            content = tvContent.getText().toString();
        }

        if (etContent.getVisibility() == VISIBLE) {
            content = etContent.getText().toString();
        }
        return content;
    }

    //给控件设置标题
    public void setTitle(String content) {
        if (TextUtils.isEmpty(content)) {
            tvTitle.setText("");
            return;
        }
        tvTitle.setText(content);
    }

    //设置是否可以编辑
    public void setIsEdit(boolean edit) {
        isEditor = edit;
        if (isEditor) {
            tvContent.setVisibility(GONE);
            etContent.setVisibility(VISIBLE);
        } else {
            tvContent.setVisibility(VISIBLE);
            etContent.setVisibility(GONE);
        }
    }

    //获取输入内容
    public EditText getEditText() {
        return etContent;
    }

    //获取右侧可点击图片
    public ImageView getRightImgView() {
        return ivRightImg;
    }

    //设置右侧点击控件
    public void setRightDialog() {
        llRightImg.setVisibility(VISIBLE);
        ivRightImg.setImageResource(R.drawable.arrow_right_black_24dp);
    }

    //设置右侧向下点击事件
    public void setRightDownDialog(boolean isShowDialog) {
        if (isShowDialog) {
            llRightImg.setVisibility(VISIBLE);
            ivRightImg.setImageResource(R.drawable.arrow_down_black_24dp);
        }
    }

    //设置右侧添加点击事件
    public void setRightAddDialog(boolean isVisible) {
        if (isVisible) {
            llRightImg.setVisibility(VISIBLE);
            ivRightImg.setImageResource(R.drawable.icon_addimg);
        }
    }


    /**
     * 保留几位小数
     * @pointNumber    获取内容时使用 传入数字的值 保留小数点后面的值
     */
    public String setContentPoint(int pointNumber) {
        String content = "";
        if (tvContent.getVisibility() == VISIBLE) {
            content = tvContent.getText().toString();
        }

        if (etContent.getVisibility() == VISIBLE) {
            content = etContent.getText().toString();
        }

        BigDecimal decimal = new BigDecimal(content);
        BigDecimal bigDecimal = decimal.setScale(pointNumber, BigDecimal.ROUND_HALF_UP);
        return bigDecimal.toString();
    }
    //设置只能输入小数点和数字
    public void setEditTextInputNumberAndPoint() {
        if (etContent.getVisibility() == VISIBLE) {
            etContent.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
    }
    //设置只能输入数字
    public void setEditTextInputNumber() {
        if (etContent.getVisibility() == VISIBLE) {
            etContent.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
    }

    //设置获取焦点
    public void setViewEditFocus() {
        if (etContent.getVisibility() == VISIBLE) {
            etContent.setFocusable(true);
            etContent.setFocusableInTouchMode(true);
            etContent.requestFocus();
            etContent.findFocus();
        }
    }
}
