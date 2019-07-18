package com.imooc.vo;

/**
 * Http请求返回最外层对象
 * Created by msi on 2019/7/14.
 */
public class ResultVo<T> {


    private  Integer code;

    private  String msg;

    /* 具体内容*/private   T data;



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
