package com.imooc.repository;

import com.imooc.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterResipotoryTest {


    @Autowired
    private  OrderMasterResipotory resipotory;

    private  final  String OPENID="110110";
    @Test
    public void testSave() throws Exception {

        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("师兄");
        orderMaster.setBuyerPhone("1232332443");
        orderMaster.setOrderAmount(new BigDecimal(3.2));
        orderMaster.setBuyerAddress("四川省成都市");
        orderMaster.setBuyerOpenid(OPENID);
        OrderMaster save = resipotory.save(orderMaster);
        Assert.assertNotNull(save);

    }
    @Test
    public void testFindByBuyerOpenId() throws Exception {

        PageRequest request = PageRequest.of(0,2);

        Page<OrderMaster> pa = resipotory.findByBuyerOpenid(OPENID, request);

        Assert.assertNotEquals(0,pa.getTotalElements());

/*
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("师兄");
        orderMaster.setBuyerPhone("1232332443");
        orderMaster.setOrderAmount(new BigDecimal(3.2));
        orderMaster.setBuyerOpenid(OPENID);
        OrderMaster save = resipotory.save(orderMaster);
        Assert.assertNotNull(save);
*/

    }
}