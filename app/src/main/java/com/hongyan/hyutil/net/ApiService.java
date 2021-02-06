package com.hongyan.hyutil.net;

import com.hongyan.hyutil.model.wanandroid.entity.WanAndroidBannerBean;
import com.hongyan.hyutil.model.wanandroid.entity.WanAndroidBean;

import io.reactivex.Observable;
import retrofit2.http.HTTP;

/**
 * @author hongyan
 */
public interface ApiService {

    @HTTP(method = "GET", path = Api.WAN_ANDROID_DATA, hasBody = false)
    Observable<WanAndroidBean> getWanAndroidData();


    @HTTP(method = "GET", path =  Api.HOME_BANNER, hasBody = false)
    Observable<WanAndroidBannerBean> getWanAndroidHomeBanner();


//
//    @POST(Api.UPDATE_PASSWORD)
//    Observable<String> updatePassword(@Body RequestBody requestBody);

}

