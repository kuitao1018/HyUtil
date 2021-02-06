package com.hongyan.hyutil.model.wanandroid;


import com.hongyan.hyutil.model.wanandroid.entity.WanAndroidBannerBean;
import com.hongyan.hyutil.model.wanandroid.entity.WanAndroidBean;
import com.hongyan.hyutil.mvp.BasePresenterImpl;
import com.hongyan.hyutil.net.RetrofitFactory;
import com.hongyan.hyutil.utils.L;
import com.hongyan.hyutil.utils.UiUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class WanAndroidPresenter extends BasePresenterImpl<WanAndroidContract.View> implements WanAndroidContract.Presenter {

    @Override
    public void loadingNetData() {
        mView.showLoading();
/**传参方法一*/
//        SortedMap<String, Object> itemMap = new TreeMap<>();
//        SortedMap<String, Object> map = new TreeMap<>();
//        String userID = PreferenceUtils.getInstance(mView.getContext()).getString(Constant.LOGIN_USER_ID_KEY);
//        map.put("userID", userID);
//        itemMap.put("data", map);

/**传参方法二*/
//        Map<String, String> map = new HashMap<>();
//        map.put("current", page + "");
//        map.put("size", "10");


//        RetrofitFactory.getInstance().api()
//                .getWanAndroidData()
//                .compose(UiUtils.io_main())
//                .as(bindLifecycle())
//                .subscribe(new BaseObserver<WanAndroidBean>() {
//                    @Override
//                    protected void onSuccess(WanAndroidBean wanAndroidBean) throws Exception {
//                        List<WanAndroidBean.DataBean.DatasBean> datasBeans = wanAndroidBean.getData().getDatas();
//                        L.e("返回参数 - " + datasBeans);
//                        mView.onLoadingSuccess(datasBeans);
//                    }
//
//                    @Override
//                    protected void onFailure(String error, boolean isNetWorkError) throws Exception {
//                        mView.onLoadingFailed(error);
//                    }
//                });
//
//    }


        RetrofitFactory.getInstance().api()
                .getWanAndroidData()
                .compose(UiUtils.io_main())
                .as(bindLifecycle())
                .subscribe(new Observer<WanAndroidBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        L.e("","--d--");

                    }

                    @Override
                    public void onNext(WanAndroidBean wanAndroidBean) {
                        List<WanAndroidBean.DataBean.DatasBean> datasBeans = wanAndroidBean.getData().getDatas();
                        L.e("","返回参数 - " + datasBeans);
                        mView.onLoadingSuccess(datasBeans);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onLoadingFailed(e.toString());
                        mView.hideLoading();

                    }

                    @Override
                    public void onComplete() {
                        L.e("","--onComplete--");
                        mView.hideLoading();
                    }
                });

    }


    @Override
    public void loadingBannerNetData() {
        mView.showLoading();
        RetrofitFactory.getInstance().api()
                .getWanAndroidHomeBanner()
                .compose(UiUtils.io_main())
                .as(bindLifecycle())
                .subscribe(new Observer<WanAndroidBannerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WanAndroidBannerBean wanAndroidBannerBean) {

                        mView.onLoadingBannerSuccess(wanAndroidBannerBean.getData());

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
