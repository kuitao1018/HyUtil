package com.hongyan.hyutil.model.custom;

import android.view.LayoutInflater;
import android.view.View;

import com.contrarywind.adapter.WheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.hongyan.hyutil.R;
import com.hongyan.hyutil.model.PasswordInputActivity;
import com.hongyan.hyutil.mvp.BaseActivity;
import com.hongyan.hyutil.utils.L;
import com.hongyan.hyutil.utils.T;
import com.hongyan.hyutil.widget.BottomDialog;
import com.hongyan.hyutil.widget.CustomWheelView;
import com.hongyan.hyutil.widget.UsualDialogger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * author: Administrator
 * created on: 2019/6/20 12:51
 * description: 自定义
 */
public class BaseCustomViewActivity extends BaseActivity {

    UsualDialogger dialog2;

    @Override
    public int setLayoutId() {
        return R.layout.activity_custom_view;
    }

    public void btnShow1(View view) {
        startActivity(CustomView01Activity.class);
    }

    public void btnShow3(View view) {
        startActivity(PasswordInputActivity.class);
    }

    public void btnShow4(View view) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_show_trigon, null);
        new BottomDialog(BaseCustomViewActivity.this, inflate, R.style.custom_dialog).show();

    }

    public void btnShow5(View view) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_show_wheelview, null);
        new BottomDialog(BaseCustomViewActivity.this, inflate, R.style.custom_dialog).show();
        CustomWheelView customWheelView = inflate.findViewById(R.id.custom_wheel_view);
        List<String> list = Arrays.asList("湖北", "湖南", "广州", "合肥", "黑龙江", "大连");
        // 初始化仿IOS滚轮效果的数据
        // 在这里可以设置滚轮的偏移量
        customWheelView.setOffset(2);
        //设置每一个Item中的数据 mArrayList中装着的是一堆String字符串
        customWheelView.setItems(list);
        customWheelView.setOnWheelViewListener(new CustomWheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                L.e("数据为 ", "索引 " + selectedIndex + " " + "数据 " + item);
            }
        });
    }

    public void btnShow6(View view) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_show_wheelview2, null);
        new BottomDialog(BaseCustomViewActivity.this, inflate, R.style.custom_dialog).show();
        WheelView wheelView = inflate.findViewById(R.id.wheelview);

        wheelView.setCyclic(false);

        final List<String> mOptionsItems = new ArrayList<>();
        mOptionsItems.add("item0");
        mOptionsItems.add("item1");
        mOptionsItems.add("item2");

        wheelView.setAdapter(new ArrayWheelAdapter(mOptionsItems));
        wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                T.show(mOptionsItems.get(index));
            }
        });
    }

    public class ArrayWheelAdapter<T> implements WheelAdapter {
        private List<T> items;

        public ArrayWheelAdapter(List<T> items) {
            this.items = items;

        }

        @Override
        public Object getItem(int index) {
            if (index >= 0 && index < items.size()) {
                return items.get(index);
            }
            return "";
        }

        @Override
        public int getItemsCount() {
            return items.size();
        }

        @Override
        public int indexOf(Object o) {
            return items.indexOf(o);
        }
    }

    //dialog 样式
    public void btnShow7(View view) {
        dialog2 = UsualDialogger.Builder(this)
                .setTitle("通用对话框")
                .setMessage("这是一个漂亮的对话框")
                .setOnConfirmClickListener("确定", view1 -> {
                    T.show("确定");
                })
                .setOnCancelClickListener("取消", view2 -> {
                    T.show("取消");
                    if (dialog2 != null) {
                        dialog2.dismiss();
                    }
                })
                .build()
                .shown();
    }
}
