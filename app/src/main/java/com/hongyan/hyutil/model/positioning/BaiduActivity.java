package com.hongyan.hyutil.model.positioning;

import android.os.Bundle;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.hongyan.hyutil.R;
import com.hongyan.hyutil.mvp.BaseActivity;
import com.hongyan.hyutil.widget.CustomToolBar;

/**
 * *******************************
 *
 * @Created by Android Studio.
 * @Author: hongyan.tao
 * @Date: 2019/12/18  14:02
 * @Description 这个类的用途
 * ******************************
 */
public class BaiduActivity extends BaseActivity {
    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private LocationClient mLocationClient;

    @Override
    public int setLayoutId() {
        return R.layout.activity_baidu;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        CustomToolBar customToolBar = findViewById(R.id.ctb_title);
        customToolBar.setLeftBtListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
    }

    @Override
    public void initData() {
        super.initData();
        mBaiduMap = mMapView.getMap();
        //设置定位
        mBaiduMap.setMyLocationEnabled(true);

        //定位初始化
        mLocationClient = new LocationClient(this);
        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        //设置locationClientOption
        mLocationClient.setLocOption(option);
        //注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
        //开启地图定位图层
        mLocationClient.start();

//       LocationMode.FOLLOWING;//定位跟随态
//       LocationMode.NORMAL;   //默认为 LocationMode.NORMAL 普通态
//       LocationMode.COMPASS;  //定位罗盘态
//       BitmapDescriptor currentMarker = BitmapDescriptorFactory.fromResource(R.drawable.ic_add_image);
        MyLocationConfiguration confit = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true, null);
        // 红色
        confit.accuracyCircleFillColor = ContextCompat.getColor(this,R.color.app_color_dw);
        mBaiduMap.setMyLocationConfiguration(confit);

    }


    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
        }
    }


    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mLocationClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }
}
