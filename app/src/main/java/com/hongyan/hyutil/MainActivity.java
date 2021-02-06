package com.hongyan.hyutil;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;

import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.hongyan.hyutil.model.PubActivity;
import com.hongyan.hyutil.model.home.find.FindFragment;
import com.hongyan.hyutil.model.home.home.HomeFragment;
import com.hongyan.hyutil.model.home.me.MeFragment;
import com.hongyan.hyutil.mvp.BaseActivity;
import com.hongyan.hyutil.utils.T;
import com.hongyan.hyutil.widget.CannotSlideViewPager;
import com.hongyan.hyutil.widget.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author 主页
 * 捕获异常日志的方法   SpiderMan.show(e);
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.view_pager)
    CannotSlideViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    private String[] titles = {"首页", "发布", "我的"};
    private static boolean isExit = false;
    private long times = System.currentTimeMillis();
    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    public int setLayoutId() {
        return R.layout.activity_main_list;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        initViewPagerAdapter();
    }

    private void initViewPagerAdapter() {
        List<Fragment> fragments = new ArrayList<>();
        for (String title : titles) {
            tabLayout.addTab(tabLayout.newTab().setText(title));
        }
        HomeFragment homeFragment = new HomeFragment();
        FindFragment findFragment = new FindFragment();
        MeFragment meFragment = new MeFragment();
        fragments.add(homeFragment);
        fragments.add(findFragment);
        fragments.add(meFragment);

        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), titles, fragments);
        viewPager.setAdapter(mViewPagerAdapter);
        viewPager.setOffscreenPageLimit(titles.length);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1) {
                    PubActivity.show(MainActivity.this);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            T.show("再按一次退出程序");
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }


}



