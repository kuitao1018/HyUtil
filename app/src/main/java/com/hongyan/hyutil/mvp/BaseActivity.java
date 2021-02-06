package com.hongyan.hyutil.mvp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.hongyan.hyutil.ActivityManager;
import com.hongyan.hyutil.utils.UiUtils;
import com.uber.autodispose.AutoDisposeConverter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author 这个类是object 的子类
 */
public abstract class BaseActivity extends FragmentActivity {

    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //设置布局
        setContentView(setLayoutId());
        mBind = ButterKnife.bind(this);
        //入栈
        ActivityManager.getInstance().addActivity(this);
        //初始化控件
        initView(savedInstanceState);
        //设置数据
        initData();
    }

    /**
     * 1. 设置布局
     */
    public abstract int setLayoutId();

    /**
     * 2. 初始化布局
     *
     * @param savedInstanceState
     */
    public void initView(Bundle savedInstanceState) {
    }


    /**
     * 3.  设置数据
     */
    public void initData() {
    }

    protected <T> AutoDisposeConverter<T> bindLifecycle() {
        return UiUtils.bindLifecycle(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //出栈
        ActivityManager.getInstance().removeActivity(this);
        mBind.unbind();
    }

    public void startActivity(Class<?> aClass) {
        //防快速点击
        if (UiUtils.readyForStartActivity()) {
            startActivity(new Intent(this, aClass));
        }
    }

    public void startActivity(Class<?> aClass, Bundle bundle) {
        //防快速点击
        if (UiUtils.readyForStartActivity()) {
            Intent intent = new Intent(this, aClass);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    public void startActivityForResult(Class<?> aClass, int intentCode, Bundle bundle) {
        //防快速点击
        if (UiUtils.readyForStartActivity()) {
            Intent intent = new Intent(this, aClass);
            intent.putExtras(bundle);
            startActivityForResult(intent, intentCode);
        }
    }

    public void startActivityForResult(Class<?> aClass, int intentCode) {
        //防快速点击
        if (UiUtils.readyForStartActivity()) {
            Intent intent = new Intent(this, aClass);
            startActivityForResult(intent, intentCode);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        // app 全灰色模式
//        try {
//            if ("FrameLayout".equals(name)) {
//                int count = attrs.getAttributeCount();
//                for (int i = 0; i < count; i++) {
//                    String attributeName = attrs.getAttributeName(i);
//                    String attributeValue = attrs.getAttributeValue(i);
//                    if (attributeName.equals("id")) {
//                        int id = Integer.parseInt(attributeValue.substring(1));
//                        String idVal = getResources().getResourceName(id);
//                        if ("android:id/content".equals(idVal)) {
//                            GrayFrameLayout grayFrameLayout = new GrayFrameLayout(context, attrs);
////                            grayFrameLayout.setWindow(getWindow());
//                            grayFrameLayout.setBackgroundDrawable(getWindow().getDecorView().getBackground());
//                            return grayFrameLayout;
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return super.onCreateView(name, context, attrs);
    }

}





