package com.hongyan.hyutil.model;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hongyan.hyutil.R;
import com.hongyan.hyutil.mvp.BaseActivity;
import com.hongyan.hyutil.utils.T;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author: hongyan.tao
 * @created on: 2019/8/26 10:18
 * @description: 侧滑功能
 */
public class LateralSpreadActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    public int setLayoutId() {
        return R.layout.activity_lateral_spread;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(i);
        }
        BaseQuickAdapter adapter = new BaseQuickAdapter(R.layout.adapter_lateral_spread, list) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, Object item) {
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.addChildLongClickViewIds(R.id.tv_delete);
        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            T.show(position + "");
        });
    }
}
