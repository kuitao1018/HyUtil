package com.hongyan.hyutil.model.pay;


import com.hongyan.hyutil.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 *
 * @author Administrator
 */

public class PayPresenter extends BasePresenterImpl<PayContract.View> implements PayContract.Presenter {
//    @Override
//    public void getAliPayOrder(String app_id) {
//        mView.showLoading();
//        SortedMap<String, Object> map = new TreeMap<>();
//        map.put("app_id", app_id);
//        MainDataRepository.getInstance().loadingAliPayOrder(map)
//                //网络是耗时操作,所以在io线程中去执行
//                .subscribeOn(Schedulers.io())
//                //请求成功后回到主线程中
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<AliPayModel>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        addDisposable(d);
//                    }
//
//                    @Override
//                    public void onNext(AliPayModel aliPayModel) {
//                        L.e("请求成功");
//                        try {
//                            if (aliPayModel != null) {
//                                mView.onLoadingAliPaySuccess(aliPayModel);
//                            } else {
//                                mView.onLoadingAliPayFailed(aliPayModel.getData().toString());
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mView.hideLoading();
//                        mView.onLoadingAliPayFailed("未知错误");
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        mView.hideLoading();
//                    }
//                });
//    }
//
//    @Override
//    public void getWxPayOrder(final String wxAppId) {
//        mView.showLoading();
//        SortedMap<String, Object> map = new TreeMap<>();
//        map.put("app_id", wxAppId);
//        MainDataRepository.getInstance().loadingWxPayOrder(map)
//                //网络是耗时操作,所以在io线程中去执行
//                .subscribeOn(Schedulers.io())
//                //请求成功后回到主线程中
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<WxpayModel>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        addDisposable(d);
//                    }
//
//                    @Override
//                    public void onNext(WxpayModel wxpayModel) {
//                        L.e("请求成功");
//                        try {
//                            if (wxpayModel != null) {
//                                mView.onLoadingWxPaySuccess(wxpayModel);
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            mView.onLoadingWxPayFailed(wxpayModel.getData().toString());
//                        }
//                    }
//
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mView.hideLoading();
//                        mView.onLoadingWxPayFailed("未知错误");
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        mView.hideLoading();
//                    }
//                });
//
//    }
}
