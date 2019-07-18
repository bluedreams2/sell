package com.imooc.exception;

import com.imooc.enums.ResultEnum;

//统一异常
public class SellException extends  RuntimeException {


    private  Integer code;

    private  String message;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String defaultMessage) {
        this.code = code;
        this.message = message;
    }
}
