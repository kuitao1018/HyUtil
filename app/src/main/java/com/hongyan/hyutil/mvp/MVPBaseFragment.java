package com.hongyan.hyutil.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hongyan.hyutil.utils.UiUtils;
import com.simple.spiderman.SpiderMan;
import com.uber.autodispose.AutoDisposeConverter;

import java.lang.reflect.ParameterizedType;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class MVPBaseFragment<V extends BaseView, T extends BasePresenterImpl<V>> extends Fragment implements BaseView {
    private T mPresenter;
    private Activity mActivity;
    private View mConvertView;
    private Unbinder mBind;
    //是否加载完成
    private Boolean isLoadingCompleted = true;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = getInstance(this, 1);
        mPresenter.onCreate(this);
        mPresenter.attachView((V) this);
    }


    @Override
    public Context getContext() {
        return super.getContext();
    }

    public <T> T getInstance(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            SpiderMan.show(e);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            SpiderMan.show(e);
            e.printStackTrace();
        } catch (ClassCastException e) {
            SpiderMan.show(e);
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
            SpiderMan.show(e);
        }
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == mConvertView) {
            mConvertView = inflater.inflate(setLayoutId(), container, false);
        }
        mBind = ButterKnife.bind(this, mConvertView);
        return mConvertView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onResume() {
        super.onResume();
        onVisible();
    }

    private void onVisible() {
        if (!isLazyLoad) {
            initData();
        } else {
            onLazyLoad();
        }
    }

    private void onLazyLoad() {
        if (isLoadingCompleted) {
            isLoadingCompleted = false;
            initData();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        onInVisible();
    }

    private void onInVisible() {

    }

    protected boolean isLazyLoad = false;

    public boolean setLazyLoad(boolean lazyLoad) {
        isLazyLoad = lazyLoad;
        return isLazyLoad;
    }

    /**
     * onLayoutRes()传入fragment要显示的布局ResId
     */
    protected abstract int setLayoutId();

    /**
     * initData()进行数据的访问，如访问网络等，调用到此方法的时候，view都已经初始化过了
     */
    protected abstract void initData();

    /**
     * initView() 进行view的绑定，view是onLayoutRes()传入的布局
     */
    protected abstract void initView();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter.onDestroy(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
        mConvertView = null;
    }

    protected <T> AutoDisposeConverter<T> bindLifecycle() {
        return UiUtils.bindLifecycle(this);
    }


    public void startActivity(Class<?> aClass) {
        //防快速点击
        if (UiUtils.readyForStartActivity()) {
            startActivity(new Intent(mActivity, aClass));
        }
    }

    public void startActivity(Class<?> aClass, Bundle bundle) {
        //防快速点击
        if (UiUtils.readyForStartActivity()) {
            Intent intent = new Intent(mActivity, aClass);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    public void startActivityForResult(Class<?> aClass, int intentCode, Bundle bundle) {
        //防快速点击
        if (UiUtils.readyForStartActivity()) {
            Intent intent = new Intent(getActivity(), aClass);
            intent.putExtras(bundle);
            startActivityForResult(intent, intentCode);
        }
    }

}
