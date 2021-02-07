package com.hongyan.hyutil.model.wanandroid;


import android.content.Intent;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyan.hyutil.R;
import com.hongyan.hyutil.model.wanandroid.activity.WanAndroidItemDataActivity;
import com.hongyan.hyutil.model.wanandroid.adapter.WanAndroidAdapter;
import com.hongyan.hyutil.model.wanandroid.entity.WanAndroidBannerBean;
import com.hongyan.hyutil.model.wanandroid.entity.WanAndroidBean;
import com.hongyan.hyutil.mvp.MVPBaseActivity;
import com.hongyan.hyutil.utils.GlideImageLoader;
import com.hongyan.hyutil.widget.CustomToolBar;
import com.hongyan.hyutil.widget.LoadingDialog;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class WanAndroidActivity extends MVPBaseActivity<WanAndroidContract.View, WanAndroidPresenter> implements WanAndroidContract.View {
    private List<String> mTitleList = new ArrayList<>();
    private List<String> mBannerIvPath = new ArrayList<>();
    private Banner mWanTitleBanner;
    private List<WanAndroidBean.DataBean.DatasBean> mDatasBeans = new ArrayList<>();
    private RecyclerView mWanListRv;
    private LoadingDialog mLoadingDialog;

    @Override
    public int setLayoutId() {
        return R.layout.activity_wan_android;
    }

    @Override
    public void initView() {
        super.initView();
        mLoadingDialog = new LoadingDialog(this, "请稍后...");
        mPresenter.loadingNetData();
        mPresenter.loadingBannerNetData();
        CustomToolBar customToolBar = findViewById(R.id.ctb_title);
        customToolBar.setLeftBtListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mWanTitleBanner = findViewById(R.id.wan_title_banner);
        mWanListRv = findViewById(R.id.wan_list_rv);
        mWanListRv.setLayoutManager(new LinearLayoutManager(this));
        //卡片
        mWanListRv.addItemDecoration(new WanAndroidItem());
    }

    @Override
    public void showLoading() {
        mLoadingDialog.show();
    }

    @Override
    public void hideLoading() {
        mLoadingDialog.hide();
    }

    @Override
    public void onLoadingSuccess(List<WanAndroidBean.DataBean.DatasBean> datasBeans) {
        mDatasBeans.clear();
        if (datasBeans.size() > 0) {
            mDatasBeans.addAll(datasBeans);
            WanAndroidAdapter wanAndroidAdapter = new WanAndroidAdapter(R.layout.adapter_wan_android, mDatasBeans);
            mWanListRv.setAdapter(wanAndroidAdapter);
            wanAndroidAdapter.setOnItemClickListener((adapter, view, position) -> {
                        WanAndroidBean.DataBean.DatasBean bean = mDatasBeans.get(position);
                        if (bean.getLink() != null) {
                            String link = bean.getLink();
                            Intent intent = new Intent(WanAndroidActivity.this, WanAndroidItemDataActivity.class);
                            intent.putExtra("link", link);
                            startActivity(intent);
                        }
                    }
            );
        }
    }

    @Override
    public void onLoadingBannerSuccess(@NotNull List<WanAndroidBannerBean.DataBean> data) {
        for (int i = 0; i < data.size(); i++) {
            WanAndroidBannerBean.DataBean wanAndroidBannerBean = data.get(i);
            //设置图片加载器
            if (wanAndroidBannerBean != null) {
                mTitleList.add(wanAndroidBannerBean.getImagePath());
                mBannerIvPath.add(wanAndroidBannerBean.getDesc());
                //轮播图
                mWanTitleBanner.setImageLoader(new GlideImageLoader());
                mWanTitleBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
                mWanTitleBanner.update(mTitleList, mBannerIvPath);
                mWanTitleBanner.start();
            }
        }
    }

    @Override
    public void onLoadingFailed(String error) {
        mLoadingDialog.hide();
    }

    private static class WanAndroidItem extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0, 10, 0, 10);

        }
    }
}
