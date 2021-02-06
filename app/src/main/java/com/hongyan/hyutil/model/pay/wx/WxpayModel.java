package com.hongyan.hyutil.model.pay.wx;


import java.io.Serializable;

/**
 * Created by 微信实体类
 */

public class WxpayModel implements Serializable {

    /**
     * data : “dsadsadsaaddsa”
     */

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
