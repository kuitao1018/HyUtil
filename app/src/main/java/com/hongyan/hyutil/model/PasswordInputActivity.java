package com.hongyan.hyutil.model;

import android.os.Bundle;

import com.hongyan.hyutil.R;
import com.hongyan.hyutil.mvp.BaseActivity;
import com.hongyan.hyutil.utils.PasswordInputView;
import com.hongyan.hyutil.utils.T;

/**
 * @author: hongyan.tao
 * @created on: 2019/10/31 17:38
 * @description:
 */
public class PasswordInputActivity extends BaseActivity {
    @Override
    public int setLayoutId() {
        return R.layout.activity_password_input;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        PasswordInputView passwordView = findViewById(R.id.passwordView);
        passwordView.setInputListener(new PasswordInputView.InputListener() {
            @Override
            public void onInputCompleted(String text) {
                T.show(text);
            }
        });
    }
}
