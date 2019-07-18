package com.imooc.enums;

/**
 * Created by msi on 2019/7/14.
 */
public enum  ResultEnum {

    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"商品库存不正确")
    ,ORDER_NOT_EXISTS(12,"订单不存在"),
    ORDERDETAIL_NOT_EXISTS(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单状态不正确"),
    ORDER_UPDATE_FAIL(15,"订单更新失败"),
    ORDER_DETAIL_EMPTY(16,"订单中商品为空"), PARAM_ERROR(1,"参数不正确" );

    private  Integer code;

    private  String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

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
