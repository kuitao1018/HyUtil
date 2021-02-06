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
import com.simple.spiderman.SpiderMan;
import com.uber.autodispose.AutoDisposeConverter;

import java.lang.reflect.ParameterizedType;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class MVPBaseActivity<V extends BaseView, T extends BasePresenterImpl<V>> extends FragmentActivity implements BaseView {

    public T mPresenter;
    private Unbinder mBind;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mBind = ButterKnife.bind(this);
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActivityManager.getInstance().addActivity(this);
        mPresenter = getInstance(this, 1);
        mPresenter.attachView((V) this);
        mPresenter.onCreate(this);
        setContentView(setLayoutId());
        mBind = ButterKnife.bind(this);
        initView();
        initDate();
    }


    public void initView() {
    }

    public void initDate() {

    }

    protected abstract int setLayoutId();


    protected <T> AutoDisposeConverter<T> bindLifecycle() {
        return UiUtils.bindLifecycle(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter.onDestroy(this);
        }

        ActivityManager.getInstance().removeActivity(this);
        mBind.unbind();
    }

    @Override
    public Context getContext() {
        return this;
    }

    public <T> T getInstance(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass().getGenericSuperclass())).getActualTypeArguments()[i]).newInstance();
        } catch (InstantiationException e) {
            SpiderMan.show(e);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            SpiderMan.show(e);
            e.printStackTrace();
        } catch (ClassCastException e) {
            SpiderMan.show(e);
            e.printStackTrace();
        }
        return null;
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
}
