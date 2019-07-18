package com.imooc.service.impl;

import com.imooc.dataobject.OrderDetail;
import com.imooc.dto.OrderDTO;
import com.imooc.enums.OrderStatus;
import com.imooc.utils.KeyUtils;
import junit.framework.Assert;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {


    @Autowired
    private  OrderServiceImpl orderService;

    private  final  String OPENID="110110";

    @Test
    public void testCreate() throws Exception {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerOpenid(OPENID);
        orderDTO.setOrderAmount(new BigDecimal(3.2));
        orderDTO.setOrderId(KeyUtils.genUniqueKey());
        orderDTO.setBuyerName("xcb");
        orderDTO.setBuyerPhone("111111111");
        orderDTO.setCreateTime(new Date());
        orderDTO.setUpdateTime(new Date())
        ;
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("12345678");
        o1.setProductQuantity(1);

        orderDetails.add(o1);
        orderDTO.setOrderDetailList(orderDetails);

        OrderDTO orderDTO1 = orderService.create(orderDTO);
        log.info("【创建订单】result={} ",orderDTO1);


    }


    public  static  final String  ORDERID="";

    @Test
    public void testFindOne() throws Exception {


        OrderDTO result  = orderService.findOne(ORDERID);
        log.info("查询订单{}",result);
        Assert.assertNotNull(result);

    }

    @Test
    public void testFindList() throws Exception {

    }

    @Test
    public void testCancel() throws Exception {

    }

    private  static  final  String ORDER_ID = "";
    @Test
    public void testFinish() throws Exception {

        OrderDTO orderDTO = orderService.findOne(ORDER_ID);

        OrderDTO result = orderService.finish(orderDTO);
        org.junit.Assert.assertEquals(OrderStatus.FINISHED.getCode(),result.getOrderStatus());



    }

    @Test
    public void testPaid() throws Exception {

    }
}