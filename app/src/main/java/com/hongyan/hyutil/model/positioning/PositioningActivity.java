package com.hongyan.hyutil.model.positioning;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.GnssStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import com.hongyan.hyutil.R;
import com.hongyan.hyutil.mvp.BaseActivity;
import com.hongyan.hyutil.utils.T;

import butterknife.OnClick;

public class PositioningActivity extends BaseActivity {

    private LocationManager mLocationManager;
    private int mSatelliteCount;

    @Override
    public int setLayoutId() {
        return R.layout.activity_positioning;
    }

    @OnClick(R.id.bt_gps)
    public void onClickGps() {
        startActivity(GpsActivity.class);
    }

    @OnClick(R.id.bt_gaode)
    public void onClickGaode() {
        startActivity(GaodeActivity.class);
    }

    @OnClick(R.id.bt_baidu)
    public void onClickBaidu() {
        startActivity(BaiduActivity.class);
    }

    @OnClick(R.id.bt_bd)
    public void onClickBd() {
        initLocation();
    }

    /**
     * 初始化定位管理,android自带卫星
     */
    private void initLocation() {
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //判断GPS是否正常启动
        if (!mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            T.show("请开启GPS导航");
            //返回开启GPS导航设置界面
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent, 0);
            return;
        }
        //添加卫星状态改变监听
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mLocationManager.registerGnssStatusCallback(new LocaCallback());
        }
        //1000位最小的时间间隔，1为最小位移变化；也就是说每隔1000ms会回调一次位置信息
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                Log.e("onLocationChanged", "经度：" + location.getLongitude() + "纬度：" + location.getLatitude());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public class LocaCallback extends GnssStatus.Callback {
        @Override
        public void onSatelliteStatusChanged(GnssStatus status) {
            super.onSatelliteStatusChanged(status);
            //satelliteCount=
            mSatelliteCount = status.getSatelliteCount();
            //解析组装卫星信息
            makeGnssStatus(status, mSatelliteCount);
        }

        @Override
        public void onStarted() {
            super.onStarted();
        }

        @Override
        public void onStopped() {
            super.onStopped();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void makeGnssStatus(GnssStatus status, int satelliteCount) {
        //当前可以获取到的卫星总数，然后遍历
        if (satelliteCount > 0) {
            for (int i = 0; i < satelliteCount; i++) {
                //GnssStatus的大部分方法参数传入的就是卫星数量的角标
                //获取卫星类型
                int type = status.getConstellationType(i);
                if (GnssStatus.CONSTELLATION_BEIDOU == type) {
                    //北斗卫星类型的判断
                    T.show("北斗卫星类型的判断");
                }
            }
        }
    }
}
