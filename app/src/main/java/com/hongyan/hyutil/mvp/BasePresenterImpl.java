package com.hongyan.hyutil.mvp;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.hongyan.hyutil.utils.UiUtils;
import com.uber.autodispose.AutoDisposeConverter;

public class BasePresenterImpl<V extends BaseView> implements BasePresenter<V> {
    protected V mView;
    private LifecycleOwner lifecycleowner;

    @Override
    public void attachView(V view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void onCreate(LifecycleOwner owner) {
        this.lifecycleowner = owner;
    }

    @Override
    public void onDestroy(LifecycleOwner owner) {
        this.lifecycleowner = null;
    }

    @Override
    public void onLifecycleChanged(LifecycleOwner owner, Lifecycle.Event event) {
        Log.e("onLifecycleChanged", owner.getLifecycle().toString() + "--" + event.name());
    }


    protected <T> AutoDisposeConverter<T> bindLifecycle() {
        return UiUtils.bindLifecycle(lifecycleowner);
    }


}
