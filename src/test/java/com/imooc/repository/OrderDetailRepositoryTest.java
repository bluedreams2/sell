package com.imooc.repository;

import com.imooc.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {



    @Autowired
    private  OrderDetailRepository repository;
    @Test
    public void testSave() throws Exception {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("12345678");
        orderDetail.setOrderId("11");
        orderDetail.setProductId("11");
        orderDetail.setProductQuantity(1);
        orderDetail.setProductPrice(new BigDecimal(3.2));
        orderDetail.setProductIcon("http://ss");
        orderDetail.setProductName("ss");
        repository.save(orderDetail);
    }
    @Test
    public void testFindByOrderId() throws Exception {
        List<OrderDetail> byOrderId = repository.findByOrderId("11");
        Assert.assertNotNull(byOrderId);
    }


}