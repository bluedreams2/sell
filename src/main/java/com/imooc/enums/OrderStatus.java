package com.imooc.enums;

/**
 * Created by msi on 2019/7/14.
 */
public enum  OrderStatus {

     NEW(0,"新订单"),FINISHED(1,"完结"),CANCEL(2,"已取消");
     OrderStatus(Integer code, String message) {
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
