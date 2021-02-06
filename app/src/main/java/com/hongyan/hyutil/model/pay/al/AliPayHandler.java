package com.hongyan.hyutil.model.pay.al;

import android.os.Handler;

/**
 * 支付宝支付回调
 *
 * @author Administrator
 */

public class AliPayHandler extends Handler {
//
//    private static final int SDK_PAY_FLAG = 1;
//    private static final int SDK_PAY2_FLAG = 2;
//    private AliPayListener mPayListener;
//
//    public AliPayHandler( AliPayListener listener) {
//        this.mPayListener = listener;
//    }
//
//    @Override
//    public void handleMessage(Message msg) {
//        switch (msg.what) {
//            case SDK_PAY_FLAG:
//                @SuppressWarnings("unchecked")
//                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
//                /**
//                 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
//                 */
//                // 同步返回需要验证的信息
//                String resultInfo = payResult.getResult();
//                String resultStatus = payResult.getResultStatus();
//                // 判断resultStatus 为9000则代表支付成功
//                if (TextUtils.equals(resultStatus, "9000")) {
//                    // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//                    T.show("支付成功");
//                    mPayListener.onAliPaySuccess();
//                } else {
//                    mPayListener.onAliPayFailed();
//                    // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
//                    T.show("支付失败");
//                }
//                break;
////            case SDK_PAY2_FLAG:
////                int code = (int) msg.obj;
////                if (code == 0) {
////                    mPayListener.onAliPaySuccess();
////                }
////                break;
//            default:
//                break;
//        }
//    }
//
//    /**
//     * onAliPaySuccess 支付宝支付成功
//     * onAliPayFailed 支付宝支付失败
//     **/
//
//    public interface AliPayListener {
//        void onAliPaySuccess();
//
//        void onAliPayFailed();
//    }
}
