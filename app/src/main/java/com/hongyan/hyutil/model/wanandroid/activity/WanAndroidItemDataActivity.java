package com.hongyan.hyutil.model.wanandroid.activity;

import android.os.Bundle;
import android.webkit.WebView;

import com.hongyan.hyutil.R;
import com.hongyan.hyutil.mvp.BaseActivity;

/**
 * author: hongyan
 * created on: 2019/3/28 16:08
 * description:
 */
public class WanAndroidItemDataActivity extends BaseActivity {

    private WebView mWanDataWb;

    @Override
    public int setLayoutId() {
        return R.layout.activity_wan_android_data;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        String link = getIntent().getStringExtra("link");
        mWanDataWb = findViewById(R.id.wan_data_wb);
        mWanDataWb.loadUrl(link);
    }

    @Override
    public void initData() {

    }
}
