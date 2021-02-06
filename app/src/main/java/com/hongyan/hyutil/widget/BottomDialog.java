package com.hongyan.hyutil.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author: hongyan
 * @created on: 2019/5/22 17:19
 * @description: 底部弹窗
 */
public class BottomDialog extends Dialog {

    public BottomDialog(Context context, View layout, int style) {
        super(context, style);
        setContentView(layout);
        if (getWindow() != null) {
            Window window = getWindow();
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.gravity = Gravity.CENTER_VERTICAL;
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.getDecorView().setPadding(30, 0, 30, 0);
            params.gravity = Gravity.BOTTOM;
            window.setAttributes(params);
        }
    }

}

