package com.imooc.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by msi on 2019/7/14.
 */
@Data
@Entity
public class OrderDetail {
    @Id
    private  String  detailId;

    //订单Id
    private  String orderId;

    /* 商品Id  */
    private  String productId;
    // 商品名稱
    private BigDecimal productPrice;

    //商品数量
    private  Integer productQuantity;

    //商品图标
    private  String productIcon;


    private  String productName;



}
