package com.imooc.repository;

import com.imooc.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by msi on 2019/7/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {


    @Autowired
    private  ProductInfoRepository repository;


    @Test
    public  void saveTest(){


        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("皮蛋粥");
        productInfo.setProductDescription("好喝的粥");
        productInfo.setUpdateTime(new Date());
        productInfo.setCreateTime(new Date());
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setCategoryType(2);
        productInfo.setProductIcon("http:/xxx.jpg");
        productInfo.setProductStock(100);
        productInfo.setProductStatus(0);
        repository.save(productInfo);
    }

    @Test
    public void testFindByProductStatus() throws Exception {

        List<ProductInfo> status =
                repository.findByProductStatus(0);
        Assert.assertNotEquals(0,status.size());

    }
}