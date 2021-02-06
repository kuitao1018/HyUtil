package com.hongyan.hyutil;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import androidx.multidex.MultiDex;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.simple.spiderman.SpiderMan;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * @author by  hongyan .
 * @CreateDate 2018/7/26 14:45
 * @Class App
 */

public class BaseApplication extends Application {
    public static BaseApplication mInstance;
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mInstance = this;
        // 奔溃日志 放在其他库初始化前
        SpiderMan.init(this);
        initBugly();
        //百度定位
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(getApplicationContext());
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);

    }

    private void initBugly() {
        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            processName = getProcessName(android.os.Process.myPid());
        }
    }

    private String getProcessName(int myPid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + myPid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
                SpiderMan.show(exception);
            }
        }
        return null;
    }

    public static Context getContext() {
        return mContext;
    }

    public BaseApplication() {
    }

    public static BaseApplication getInstance() {
        if (mInstance != null) {
            return mInstance;
        } else {
            mInstance = new BaseApplication();
            mInstance.onCreate();
            return mInstance;
        }
    }


    //bugly 冲突

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(getApplicationContext());
    }
}
