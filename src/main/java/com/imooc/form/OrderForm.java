package com.imooc.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderForm {



    //卖家姓名
    @NotEmpty(message = "姓名必须填写")
    private  String name;

    //手机号
    @NotEmpty(message = "手机号必须填写")
    private   String phone;


    //址
    @NotEmpty(message = "地址必须跟天蝎")
    private   String address;

    //openid
    @NotEmpty(message = "openid 必须填写")
    private   String openid;

    //items
    @NotEmpty(message = "购物车必须传递")
    private   String items;
}
