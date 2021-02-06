package com.hongyan.hyutil.model.pay.al;

//import android.app.Activity;
//import android.os.Handler;
//import android.os.Message;
//
//import com.alipay.sdk.app.PayTask;
//import com.hongyan.utils.utils.L;

/**
 * 支付宝支付工具类
 */

public class AliPayUntil {
//    private static final int SDK_PAY_FLAG = 1;
//
//    /**
//     * @param activity
//     * @param mHandler
//     * @param model    支付宝数据
//     */
//    public static void pay(final Activity activity, final Handler mHandler, final AliPayModel model) {
//        Runnable payRunnable = new Runnable() {
//            @Override
//            public void run() {
//                PayTask aliPay = new PayTask(activity);
//                Map<String, String> result = aliPay.payV2(model.getData(), true);
//                L.e(result.toString());
//                Message msg = new Message();
//                msg.what = SDK_PAY_FLAG;
//                msg.obj = result;
//                mHandler.sendMessage(msg);
//            }
//        };
//        Thread payThread = new Thread(payRunnable);
//        payThread.start();
//    }
}
