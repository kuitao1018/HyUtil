package com.hongyan.hyutil.net;

import android.util.Log;

import com.hongyan.hyutil.utils.SpUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 收到拦截器
 */
class ReceivedCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .addHeader("currentTimeStamp", System.currentTimeMillis() + "")
                .addHeader("appVersionNo", "1")
                .addHeader("deviceNo", "1")
                .addHeader("deviceType", "1")
                .addHeader("token", SpUtils.getInstance().getString("token"))
                .build();
        Response originalResponse = chain.proceed(request);
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            //解析Cookie
            for (String header : originalResponse.headers("Set-Cookie")) {
                stringBuilder.append(header);
                if (header.contains("JSESSIONID")) {
                    String cookie = header.substring(header.indexOf("JSESSIONID"), header.indexOf(";"));
                    SpUtils.getInstance().setString("cookie", cookie);
                    Log.e("cookie", cookie);
                }
            }
            Log.e("cookie全部", stringBuilder.toString());
        }
        return originalResponse;
    }
}
