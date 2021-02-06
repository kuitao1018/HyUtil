package com.hongyan.hyutil.model.wanandroid.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hongyan.hyutil.R;
import com.hongyan.hyutil.model.wanandroid.entity.WanAndroidBean;

import java.util.List;

/**
 * author: hongyan
 * created on: 2019/3/28 15:49
 * description:
 */
public class WanAndroidAdapter extends BaseQuickAdapter<WanAndroidBean.DataBean.DatasBean, BaseViewHolder> {

    public WanAndroidAdapter(int adapter_wan_android, List<WanAndroidBean.DataBean.DatasBean> datasBeans) {
        super(adapter_wan_android, datasBeans);
    }

    @Override
    public void convert(BaseViewHolder helper, WanAndroidBean.DataBean.DatasBean item) {
        helper.setText(R.id.wan_item_title, item.getTitle())
                .setText(R.id.wan_item_date, item.getNiceDate())
                .setText(R.id.wan_item_name, "作者:" + item.getShareUser());
    }
}
