package com.hongyan.hyutil.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.hongyan.hyutil.R;


/**
 * @author 通用的加载弹窗
 */
public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context, String msg) {
        super(context, R.style.basic_dialog);
        setContentView(R.layout.dialog_loading);
        if (null != msg) {
            ((TextView) findViewById(R.id.tv_msg)).setText(msg);
        }
        // 设置Dialog参数
        Window window = getWindow();
        WindowManager.LayoutParams params = null;
        if (window != null) {
            params = window.getAttributes();
            params.gravity = Gravity.CENTER;
            window.setAttributes(params);
            window.setWindowAnimations(R.style.PopWindowAnimStyle);
            setCanceledOnTouchOutside(false);
            setCancelable(false);
        }
    }

    public void setContent(String msg) {
        ((TextView) findViewById(R.id.tv_msg)).setText(msg);
    }

    @Override
    public void dismiss() {
        if (isShowing()) {
            super.dismiss();
        }
    }

    @Override
    public void hide() {
        if (isShowing()) {
            super.hide();
        }
    }
}
