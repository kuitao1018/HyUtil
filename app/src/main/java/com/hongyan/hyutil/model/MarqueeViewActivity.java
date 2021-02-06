package com.hongyan.hyutil.model;

import android.os.Bundle;
import android.widget.TextView;

import com.hongyan.hyutil.R;
import com.hongyan.hyutil.mvp.BaseActivity;
import com.hongyan.hyutil.utils.T;
import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @created: by Android Studio.
 * @author: hongyan.tao
 * @date: 2020/7/2
 * @describe: 跑马灯
 */
public class MarqueeViewActivity extends BaseActivity {

    @BindView(R.id.marqueeview)
    MarqueeView marqueeview;

    @Override
    public int setLayoutId() {
        return R.layout.activity_marquee_view;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        initMarquee();
    }

    private void initMarquee() {
        List<String> info = new ArrayList<>();
        info.add("1.第一行第一行第一行第一行第一行");
        info.add("2. 第二行第二行第二行第二行第二行");
        info.add("3. 第三行第三行第三行第三行第三行第三行第三行");
        marqueeview.startWithList(info);
        // 在代码里设置自己的动画
        marqueeview.startWithList(info, R.anim.anim_bottom_in, R.anim.anim_top_out);
        //设置跑马灯的点击事件
        marqueeview.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                T.show(". " + textView.getText());
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        marqueeview.startFlipping();
    }

    @Override
    public void onStop() {
        super.onStop();
        marqueeview.stopFlipping();
    }


}
