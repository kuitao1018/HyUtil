package com.hongyan.hyutil.net;

import android.util.Log;

import com.hongyan.hyutil.utils.SpUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 添加拦截器
 */
class AddCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            //解析Cookie
            for (String header : originalResponse.headers("Set-Cookie")) {
                stringBuilder.append(header);
                if (header.contains("JSESSIONID")) {
                    String cookie = header.substring(header.indexOf("JSESSIONID"), header.indexOf(";"));
                    SpUtils.getInstance().setString("cookie", cookie);
                    Log.e("cookie", "" + cookie);
                }
            }
            Log.e("cookie全部-----   ", stringBuilder.toString());
        }
        return originalResponse;

    }
}
