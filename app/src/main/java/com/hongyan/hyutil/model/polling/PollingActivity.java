package com.hongyan.hyutil.model.polling;


import android.annotation.SuppressLint;
import android.widget.TextView;

import com.hongyan.hyutil.R;
import com.hongyan.hyutil.mvp.MVPBaseActivity;
import com.hongyan.hyutil.utils.L;
import com.hongyan.hyutil.utils.UiUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class PollingActivity extends MVPBaseActivity<PollingContract.View, PollingPresenter> implements PollingContract.View {
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_content2)
    TextView tvContent2;
    private Disposable subscribe1;
    private Disposable subscribe2;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_polling;
    }


    @SuppressLint("AutoDispose")
    @OnClick(R.id.bt_start)
    public void onClickBtStart() {
        //interval对应参数 ：首次执行延时时间 、 每次轮询间隔时间 、 时间类型
        subscribe1 = Observable.interval(0, 1, TimeUnit.SECONDS)
                .compose(UiUtils.io_main())
                .as(bindLifecycle())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        L.e("type1", "accept: " + aLong);
                        tvContent.setText("轮询次数 " + aLong);
                    }
                });
    }

    @OnClick(R.id.bt_end)
    public void onClickBtEnd() {
        //停止轮询，销毁这个Subscribe1;
        if (!subscribe1.isDisposed()) {
            subscribe1.dispose();
        }
    }

    @SuppressLint("AutoDispose")
    @OnClick(R.id.bt_start2)
    public void onClickBtStart2() {
        // 有限制次数的轮询器
        subscribe2 = Observable.intervalRange(1, 10, 1, 1, TimeUnit.SECONDS)
                .compose(UiUtils.io_main())
                .as(bindLifecycle())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        L.e("type2", "accept: " + aLong);
                        tvContent2.setText("倒计时 " + (10 - aLong));
                    }
                });
    }

    @OnClick(R.id.bt_end2)
    public void onClickBtEnd2() {
        //停止轮询，销毁这个Subscribe2;
        if (!subscribe2.isDisposed()) {
            subscribe2.dispose();
        }
    }

}
