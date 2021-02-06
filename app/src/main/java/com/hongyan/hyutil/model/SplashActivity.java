package com.hongyan.hyutil.model;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.TextView;

import com.hongyan.hyutil.MainActivity;
import com.hongyan.hyutil.R;
import com.hongyan.hyutil.mvp.BaseActivity;
import com.hongyan.hyutil.utils.T;
import com.hongyan.hyutil.utils.UiUtils;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author: hongyan.tao
 * @created on: 2019/9/11 16:20
 * @description: 闪屏页面
 */
public class SplashActivity extends BaseActivity {
    public boolean isLaunch = false;
    @BindView(R.id.tv_skip)
    TextView tvSkip;
    //权限
    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION};

    @Override
    public int setLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initData() {
        super.initData();
        onePermission();
    }

    @SuppressLint("AutoDispose")
    private void onePermission() {
        new RxPermissions(this).requestEachCombined(permissions).subscribe(new Consumer<Permission>() {
            @Override
            public void accept(Permission permission) {
                if (permission.granted) {
                    startCount();
                } else if (permission.shouldShowRequestPermissionRationale) {
                    onePermission();  //强制开启权限
                    T.show("请同意相关权限，否则该功能无法使用");
                } else {
                    T.show("请前往系统权限设置开启相关权限，否则该功能无法使用");
                }
            }
        });
    }

    private void startCount() {
        Observable.intervalRange(1, 4, 0, 1, TimeUnit.SECONDS)
                .compose(UiUtils.io_main())
                .as(bindLifecycle())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        if (!isLaunch){
                            tvSkip.setText("跳过(" + (4 - aLong) + ")");
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        launchActivity();
                    }
                });
        tvSkip.setOnClickListener(view -> {
            isLaunch=true;
            launchActivity();
        });
    }

    private void launchActivity() {
        //进入主页面
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }
}
