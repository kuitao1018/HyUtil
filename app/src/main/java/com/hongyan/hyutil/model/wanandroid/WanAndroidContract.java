package com.hongyan.hyutil.model.wanandroid;


import com.hongyan.hyutil.model.wanandroid.entity.WanAndroidBannerBean;
import com.hongyan.hyutil.model.wanandroid.entity.WanAndroidBean;
import com.hongyan.hyutil.mvp.BasePresenter;
import com.hongyan.hyutil.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class WanAndroidContract {
    interface View extends BaseView {
        void showLoading();

        void hideLoading();

        void onLoadingSuccess(List<WanAndroidBean.DataBean.DatasBean> datasBeans);

        void onLoadingBannerSuccess(List<WanAndroidBannerBean.DataBean> data);

        void onLoadingFailed(String error);
    }

    interface Presenter extends BasePresenter<View> {
        void loadingBannerNetData();

        void loadingNetData();
    }
}
