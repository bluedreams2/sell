package com.imooc.dataobject;

import com.imooc.enums.OrderStatus;
import com.imooc.enums.PayStatus;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@DynamicUpdate  //自动更新时间
public class OrderMaster {


/*    @Id
    private  String orderId;

    private  String buyerName;

    private  String buyerPhone;

    private  String buyerAddress;

    @Column(name = "buyer_openid")
    private  String buyerOpenid;

    private BigDecimal orderAmount;

    //订单状态
    private  Integer orderStatus = OrderStatus.NEW.getCode();

    //字符状态 默认 0 未支付
    private  Integer payStatus= PayStatus.WAITEPAY.getCode();
//    创建时间
    private Date createTime;
//    跟新时间
    private  Date updateTime;*/

    /** 订单id. */
    @Id
    private String orderId;

    /** 买家名字. */
    private String buyerName;

    /** 买家手机号. */
    private String buyerPhone;

    /** 买家地址. */
    private String buyerAddress;

    /** 买家微信Openid. */
    private String buyerOpenid;

    /** 订单总金额. */
    private BigDecimal orderAmount;

    /** 订单状态, 默认为0新下单. */
    private Integer orderStatus = OrderStatus.NEW.getCode();

    /** 支付状态, 默认为0未支付. */
    private Integer payStatus = PayStatus.WAITEPAY.getCode();

    /** 创建时间. */
    private Date createTime;

    /** 更新时间. */
    private Date updateTime;

    //如何关联
    @Transient
    private List<OrderDetail> orderDetailList;
}
