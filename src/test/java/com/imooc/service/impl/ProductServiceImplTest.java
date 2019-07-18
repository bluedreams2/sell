package com.imooc.service.impl;

import com.imooc.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {


    @Autowired
    private  ProductServiceImpl productService;

    @Test
    public void testFindOne() throws Exception {
        ProductInfo productInfo =productService.findOne("123456");
        Assert.assertNotNull(productInfo);

    }

    @Test
    public void testFindUpAll() throws Exception {

        List<ProductInfo> upAll = productService.findUpAll();
        Assert.assertNotEquals(0,upAll.size());
    }

    @Test
    public void testFindAll() throws Exception {


        PageRequest pageRequest =PageRequest.of(0,2);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);

        System.out.println(productInfoPage.getTotalElements());

    }

    @Test
    public void testSave() throws Exception {
        ProductInfo p = new ProductInfo();
        p.setProductStock(200);
        p.setProductIcon("http://xxx.jpg");
        p.setUpdateTime(new Date());
        p.setCreateTime(new Date());
        p.setCategoryType(2);
        p.setProductPrice(new BigDecimal(3.3));
        p.setProductDescription("商品很好吃的");
        p.setProductName("爱你思思");
        p.setProductId("234567");
        p.setProductStatus(0);
        productService.save(p);
    }

    @Test
    public void testFindByProductStatus() throws Exception {


        PageRequest pageRequest =PageRequest.of(0,2);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);

        System.out.println(productInfoPage.getTotalElements());

    }
}