package com.imooc.enums;

/**
 * 支付状态枚举
 * Created by msi on 2019/7/14.
 */
public enum  PayStatus {

    WAITEPAY(0,"等待支付"),PAYSUCCESS(1,"支付成功");
    PayStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    private  Integer code;
    private  String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
