package com.hongyan.hyutil.model.pay.wx;

//import com.hongyan.utils.entity.Constant;
//import com.hongyan.utils.utils.L;
//import com.tencent.mm.opensdk.modelpay.PayReq;
//import com.tencent.mm.opensdk.openapi.IWXAPI;
//import com.tencent.mm.opensdk.openapi.WXAPIFactory;


/**
 * 微信支付工具类
 *
 * @author Administrator
 */

public class WeChatPayUntil {

//    /**
//     * @param activity 当前的activity
//     * @param model    微信支付数据源
//     */
//    public static void pay(Activity activity, WxpayModel model) {
//        String json = model.getData();
//        L.i(json);
//        if (json == null) {
//            L.e("请求参数为空");
//            return;
//        }
//        try {
//            JSONObject jsonobject = new JSONObject(json);
//            String appid = jsonobject.getString("appid");
//            String partnerid = jsonobject.getString("partnerid");
//            String prepayid = jsonobject.getString("prepayid");
//            String packagevalue = jsonobject.getString("package");
//            String sign = jsonobject.getString("sign");
//            String noncestr = jsonobject.getString("noncestr");
//            String timestamp = jsonobject.getString("timestamp");
//            IWXAPI api = null;
//            PayReq request = new PayReq();
//            request.appId = appid;
//            request.partnerId = partnerid;
//            request.prepayId = prepayid;
//            request.packageValue = packagevalue;
//            request.nonceStr = noncestr;
//            request.timeStamp = timestamp;
//            request.sign = sign;
//            api = WXAPIFactory.createWXAPI(activity, Constant.WX_APP_ID, true);
//            api.registerApp(Constant.WX_APP_ID);
//            api.sendReq(request);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
}
