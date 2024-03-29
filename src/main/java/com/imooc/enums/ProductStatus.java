package com.imooc.enums;

/**
 * 商品状态
 */
public enum  ProductStatus {
   UP(0,"上架"),DOWN(1,"下架架");
    private  Integer code;
    private  String message;

    ProductStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
