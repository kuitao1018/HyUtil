package com.hongyan.hyutil.model.pay;


import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.hongyan.hyutil.R;
import com.hongyan.hyutil.mvp.MVPBaseActivity;
import com.hongyan.hyutil.utils.T;
import com.hongyan.hyutil.widget.CustomToolBar;
import com.hongyan.hyutil.utils.TimeUtils;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 *
 * @author hongyan.tao
 */

public class PayActivity extends MVPBaseActivity<PayContract.View, PayPresenter> implements PayContract.View, View.OnClickListener {
    private TextView mPayDaoJiShi;
    private long TOTAL_TIME = 30 * 1000 * 60L;
    private long ONECE_TIME = 1000;
    private TextView mPayTvRmb;
    private CheckBox pay_check_iv_al;
    private CheckBox pay_check_iv_wx;
    private TextView mPay_tv_deposit_submit;

    @Override
    public int setLayoutId() {
        return R.layout.activity_alwxpay;
    }

    @Override
    public void initView() {
        super.initView();
        CustomToolBar customToolBar = findViewById(R.id.ctb_title);
        customToolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mPayDaoJiShi = findViewById(R.id.pay_dao_ji_shi);
        mPayTvRmb = findViewById(R.id.pay_tv_rmb);
        pay_check_iv_al = findViewById(R.id.pay_check_iv_al);
        pay_check_iv_wx = findViewById(R.id.pay_check_iv_wx);
        mPay_tv_deposit_submit = findViewById(R.id.pay_tv_deposit_submit);
        pay_check_iv_al.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pay_check_iv_al.isChecked()) {
                    pay_check_iv_wx.setChecked(false);
                    pay_check_iv_al.setChecked(true);
                }
            }
        });
        pay_check_iv_wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pay_check_iv_al.isChecked()) {
                    pay_check_iv_al.setChecked(false);
                    pay_check_iv_wx.setChecked(true);
                }
            }
        });
        mPay_tv_deposit_submit.setOnClickListener(this);
        pay_check_iv_al.setChecked(true);
        pay_check_iv_wx.setChecked(false);
    }

    @Override
    public void initDate() {
        super.initDate();
        mPayTvRmb.setText("9999.99");


        /**
         * CountDownTimer
         * 支付实现倒计时
         * TOTAL_TIME 总时长
         * ONECE_TIME 刷新时间
         */

        CountDownTimer countDownTimer = new CountDownTimer(TOTAL_TIME, ONECE_TIME) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                String s = TimeUtils.stampToDateH(millisUntilFinished);
//                L.e("时间为 +  " + millisUntilFinished);
                mPayDaoJiShi.setText("支付剩余时间: " + s);
            }

            @Override
            public void onFinish() {
                mPayDaoJiShi.setText("时间到...");
                finish();
            }
        };
        countDownTimer.start();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pay_tv_deposit_submit:
                if (TextUtils.isEmpty(mPay_tv_deposit_submit.getText().toString().trim())) {
                    T.show("请输入正确的金额");
                    return;
                } else {
                    if (pay_check_iv_al.isChecked()) {
                        T.show("调取al支付");
//                        mPresenter.getAliPayOrder(Constant.AL_APP_ID);

                    } else {
                        if (pay_check_iv_wx.isChecked()) {
                            T.show("调取wx支付");
//                            mPresenter.getWxPayOrder();
                        }
                    }
                }

                break;
            default:
                break;
        }
    }


//    @Override
//    public void showLoading() {
//
//    }
//
//    @Override
//    public void hideLoading() {
//
//    }
//
//    @Override
//    public void onLoadingAliPaySuccess(AliPayModel aliPayModel) {
//        AliPayHandler mHandler = new AliPayHandler(this);
//        if (aliPayModel.getData() == null) {
//            L.e("支付宝实体类为空");
//            return;
//        }
//        AliPayUntil.pay(PayActivity.this, mHandler, aliPayModel);
//    }
//
//    @Override
//    public void onLoadingAliPayFailed(String GlobalScreenshot) {
//        T.show(GlobalScreenshot);
//    }
//
//    @Override
//    public void onLoadingWxPaySuccess(WxpayModel wxpayModel) {
//        if (wxpayModel.getData() == null) {
//            L.e("微信实体类为空");
//            return;
//        }
//        WeChatPayUntil.pay(PayActivity.this, wxpayModel);
//    }
//
//    @Override
//    public void onLoadingWxPayFailed(String GlobalScreenshot) {
//        T.show(GlobalScreenshot);
//    }
//
//    @Override
//    public void onAliPaySuccess() {
////        支付成功跳转到成功页面
//    }
//
//    @Override
//    public void onAliPayFailed() {
//
//    }
}
