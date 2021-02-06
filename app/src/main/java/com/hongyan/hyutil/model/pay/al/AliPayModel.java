package com.hongyan.hyutil.model.pay.al;


import java.io.Serializable;

/**
 * @author 支付宝实体类
 */

public class AliPayModel implements Serializable {

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
