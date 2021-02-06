package com.hongyan.hyutil.net;

import android.content.Intent;
import android.util.Log;

import com.google.gson.JsonParseException;
import com.hongyan.hyutil.ActivityManager;
import com.hongyan.hyutil.BaseApplication;
import com.hongyan.hyutil.model.SplashActivity;
import com.hongyan.hyutil.base.BaseResponse;
import com.hongyan.hyutil.utils.SpUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class BaseObserver<T> implements Observer<T> {

    private static final String tag = BaseObserver.class.getSimpleName();

    //对应HTTP的状态码
    private static final String TOKEN_OUTOFDATA = "409";//token过期

    //出错提示
    private final String networkMsg = "网络错误";
    private final String cookieOutMsg = "登录过期，请重新登录";
    private final String parseMsg = "服务器数据解析错误";
    private final String unknownMsg = "未知错误";
    private final String connectMsg = "连接服务器错误,请检查网络";
    private final String connectOutMsg = "连接服务器超时,请检查网络";

    protected BaseObserver() {
    }

    @Override
    public void onNext(T response) {
        BaseResponse<T> tBaseEntity = (BaseResponse<T>) response;
//        if (tBaseEntity.isSuccess()) {
//            try {
//                onSuccess(response);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            try {
//                onCodeError(tBaseEntity.getRetCode());
//                onError(new Throwable(tBaseEntity.getRetMsg()));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        if (tBaseEntity.getData() != null) {
            try {
                onSuccess(response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                onCodeError(tBaseEntity.getErrorCode());
                onError(new Throwable(tBaseEntity.getErrorMsg()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        }


        /**
         * 返回成功
         *
         * @param t
         */
        protected abstract void onSuccess (T t) throws Exception;

        /**
         * 返回成功了,但是code错误
         *
         * @param error
         */
        protected void onCodeError (String error){
            //token过期
            if (TOKEN_OUTOFDATA.equals(error)) {//这里清空登录信息
                SpUtils.getInstance().setString("token", "");
                ActivityManager.getInstance().exit();
                Intent intent = new Intent(BaseApplication.getContext(), SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                BaseApplication.getContext().startActivity(intent);
            }
        }

        @Override
        public void onComplete () {
        }

        @Override
        public void onError (Throwable e){

            Throwable throwable = e;
            //获取最根源的异常
            while (throwable.getCause() != null) {
                e = throwable;
                throwable = throwable.getCause();
            }
            String error = null;
            if (e instanceof ConnectException) {
                Log.e(tag, "ConnectException");
                error = connectMsg;
            } else if (e instanceof HttpException) {             //HTTP错误
                Log.e(tag, "HttpException");
                HttpException httpException = (HttpException) e;
                error = e.getLocalizedMessage() + "";
            } else if (e instanceof ApiException) {    //服务器返回的错误
                ApiException apiException = (ApiException) e;
                switch (apiException.getErrorCode()) {
                    case "409"://token过期
                        error = cookieOutMsg;
                        break;
                    default:
                        error = e.getLocalizedMessage();
                }
            } else if (e instanceof JsonParseException
                    || e instanceof JSONException) {
                Log.e(tag, "JsonParseException JSONException");
                error = parseMsg;
            } else if (e instanceof IOException) {
                Log.e(tag, "IOException");
                if (e instanceof SocketTimeoutException) {
                    Log.e(tag, "SocketTimeoutException");
                    error = connectOutMsg;
                } else {
                    if ("Canceled".equals(e.getMessage()) || "Socket closed".equals(e.getMessage())) {
                        return;
                    } else {
                        error = connectMsg;
                    }
                }
            } else {
                error = e.getLocalizedMessage();
            }
            try {
                onFailure(error, false);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }


        /**
         * 返回失败
         *
         * @param error
         * @param isNetWorkError 是否是网络错误
         */
        protected abstract void onFailure (String error, boolean isNetWorkError) throws Exception;

        @Override
        public void onSubscribe (Disposable d){
        }
    }