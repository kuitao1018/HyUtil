package com.hongyan.hyutil.base;

import java.io.Serializable;
import java.util.List;


public class BaseResponse<T> implements Serializable {
    /**
     * data : [{"desc":"享学~","id":29,"imagePath":"https://www.wanandroid.com/blogimgs/fa822a30-00fc-4e0d-a51a-d704af48205c.jpeg","isVisible":1,"order":0,"title":"在下 Android &ldquo; 高手 &rdquo;！","type":0,"url":"https://mp.weixin.qq.com/s/KX9tvauMgDVHUx0yCmFnNQ"},{"desc":"","id":6,"imagePath":"https://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png","isVisible":1,"order":1,"title":"我们新增了一个常用导航Tab~","type":1,"url":"https://www.wanandroid.com/navi"},{"desc":"一起来做个App吧","id":10,"imagePath":"https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png","isVisible":1,"order":1,"title":"一起来做个App吧","type":1,"url":"https://www.wanandroid.com/blog/show/2"},{"desc":"","id":20,"imagePath":"https://www.wanandroid.com/blogimgs/90c6cc12-742e-4c9f-b318-b912f163b8d0.png","isVisible":1,"order":2,"title":"flutter 中文社区 ","type":1,"url":"https://flutter.cn/"}]
     * errorCode : 0
     * errorMsg :
     */

    private String errorCode;
    private String errorMsg;
    private List<T> data;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<T> getData() {

        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

//    private static String SUCCESS_CODE = "200";//成功的code
//    public String code;
//    public String msg;
//    public T responseData;
//
//    public String getRetCode() {
//        return code;
//    }
//
//    public void setRetCode(String retCode) {
//        this.code = retCode;
//    }
//
//    public String getRetMsg() {
//        return msg;
//    }
//
//    public void setRetMsg(String retMsg) {
//        this.msg = retMsg;
//    }
//
//    public T getData() {
//        return responseData;
//    }
//
//    public void setData(T responseData) {
//        this.responseData = responseData;
//    }
//
//
//    public boolean isSuccess() {
//        return null!=getRetCode()&&getRetCode().equals(SUCCESS_CODE);
//    }






}
