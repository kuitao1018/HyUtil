package com.hongyan.hyutil.model;

import android.os.Bundle;
import android.widget.Button;

import com.hongyan.hyutil.R;
import com.hongyan.hyutil.mvp.BaseActivity;

import butterknife.BindView;

public class CameraListActivity extends BaseActivity {
    @BindView(R.id.bt_show_camera_list)
    Button btShowCameraList;

    @Override
    public int setLayoutId() {
        return R.layout.activity_camera_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }



}
