package com.hongyan.hyutil.widget;


import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;


public class ViewPagerAdapter extends FragmentPagerAdapter {


    private String[] titles;
    private List<Fragment> fragments;
    private FragmentManager mFragmentManager;

    public ViewPagerAdapter(FragmentManager fm, String[] titles, List<Fragment> fragments) {
        super(fm);
        this.mFragmentManager = fm;
        this.titles = titles;
        this.fragments = fragments;
    }


    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // 将实例化的fragment进行显示即可。
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        mFragmentManager.beginTransaction().show(fragment).commit();
        return fragment;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = fragments.get(i);
    /*    Bundle bundle = new Bundle();
        bundle.putString("id","" + i);
        fragment.setArguments(bundle);*/
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return fragments!=null?fragments.size():0;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Fragment fragment = fragments.get(position);// 获取要销毁的fragment
        mFragmentManager.beginTransaction().hide(fragment).commit();// 将其隐藏即可，并不需要真正销毁，这样fragment状态就得到了保存
//        super.destroyItem(container, position, object);
    }
}
