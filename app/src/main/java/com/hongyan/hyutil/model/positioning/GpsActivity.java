package com.hongyan.hyutil.model.positioning;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.hongyan.hyutil.R;
import com.hongyan.hyutil.mvp.BaseActivity;
import com.hongyan.hyutil.utils.T;
import com.hongyan.hyutil.widget.CustomToolBar;
import com.hongyan.hyutil.utils.L;
import com.simple.spiderman.SpiderMan;

import java.io.IOException;
import java.util.List;

/**
 * @author: hongyan
 * @created on: 2019/4/17 16:31
 * @description: 定位功能
 */
public class GpsActivity extends BaseActivity {
    private LocationManager locationManager;
    private EditText tvLongitude, tvLatitude, tvAdminArea, tvLocality, tvRegion;
    //经度
    private String mLongitude;
    //纬度
    private String mLatitude;
    //省份
    private String mAdminArea;
    //城市
    private String mLocality;
    //区域
    private String mRegion;

    @Override
    public int setLayoutId() {
        return R.layout.activity_gps;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        CustomToolBar customToolBar = findViewById(R.id.ctb_title);
        customToolBar.setLeftBtListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvLongitude = findViewById(R.id.tv_longitude);
        tvLatitude = findViewById(R.id.tv_latitude);
        tvAdminArea = findViewById(R.id.tv_adminarea);
        tvLocality = findViewById(R.id.tv_locality);
        tvRegion = findViewById(R.id.tv_region);

        openPermission();  //权限
        loadingPositioning(); //定位功能
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null == mLongitude || null == mLatitude) {
            openPermission();  //权限
            loadingPositioning(); //定位功能
        }

    }

    //如果精准定位没有许可 或  粗糙定位没有许可  手动添加权限
    private void openPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
    }


    private void loadingPositioning() {
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        assert locationManager != null;
        if (!(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))) {
            T.show("请打开网络或GPS定位功能!");
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            this.startActivityForResult(intent, 0);
            return;
        }

        try {
            Location location = null;
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location == null) {
//                L.e("创建位置失败 = null ");
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
//            L.e(" 创建位置失败 = " + location);
            updateView(location);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 5, locationListener);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 5, locationListener);
        } catch (SecurityException e) {
            e.printStackTrace();
            SpiderMan.show(e);
        }
    }

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
//            L.e("供应商禁用.位置 = " + location);
            updateView(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    L.e("GpsActivity","可用");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    L.e("GpsActivity","失效");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    L.e("GpsActivity","暂时不可用");
                    break;
            }
        }

        @Override
        public void onProviderEnabled(String provider) {
            try {
                Location location = locationManager.getLastKnownLocation(provider);
                updateView(location);
            } catch (SecurityException e) {
                e.printStackTrace();
                SpiderMan.show(e);
            }
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    };


    /**
     * 实时更新文本内容
     */
    private void updateView(Location location) {
        Geocoder gc = new Geocoder(this);
        List<Address> addresses = null;
        if (location != null) {
            try {
                addresses = gc.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                L.e("GpsActivity","定位区域 = " + addresses);
                if (addresses.size() > 0) {

                    mLongitude = String.valueOf(location.getLongitude());
                    tvLongitude.setText(mLongitude);
                    mLatitude = String.valueOf(location.getLatitude());
                    tvLatitude.setText(mLatitude);
                    mAdminArea = addresses.get(0).getLocality();
                    tvLocality.setText(mAdminArea);
                    mLocality = addresses.get(0).getAdminArea();
                    tvAdminArea.setText(mLocality);
                    mRegion = addresses.get(0).getFeatureName();
                    tvRegion.setText(mRegion);
                }
            } catch (IOException e) {
                e.printStackTrace();
                SpiderMan.show(e);
            }
        } else {
            tvLongitude.setText("定位中");
            tvLatitude.setText("定位中");
            tvAdminArea.setText("定位中");
            tvLocality.setText("定位中");
            tvRegion.setText("定位中");

            Log.i("定位-----",mLongitude+" , "+mLatitude);
        }
    }

    @Override
    public void onDestroy() {
        try {
            locationManager.removeUpdates(locationListener);
        } catch (SecurityException e) {
            e.printStackTrace();
            SpiderMan.show(e);
        }
        super.onDestroy();
    }
}
