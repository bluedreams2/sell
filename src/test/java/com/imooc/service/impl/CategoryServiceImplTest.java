package com.imooc.service.impl;

import com.imooc.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by msi on 2019/7/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {


    @Autowired
    private  CategoryServiceImpl categoryService;



    @Test
    public void testFindOne() throws Exception {
        ProductCategory productCategory = categoryService.findOne(1);
        Assert.assertEquals(new Integer(1),productCategory.getCategoryId());
    }

    @Test
    public void testFindAll() throws Exception {
        List<ProductCategory> categoryServiceAll = categoryService.findAll();

        Assert.assertNotEquals(0,categoryServiceAll.size());
    }

    @Test
    public void testFindByCategoryTypeIn() throws Exception {

        List<ProductCategory> byCategoryTypeIn = categoryService.findByCategoryTypeIn(Arrays.asList(1, 2, 3, 4));
        Assert.assertNotEquals(0,byCategoryTypeIn.size());
    }

    @Test
    @Transactional
    public void testSave() throws Exception {

        ProductCategory p = new ProductCategory();
        p.setCategoryName("测试添加累呗");
        p.setCategoryType(3);
        p.setCreateTime(new Date());
        p.setUpdateTime(new Date());
        categoryService.save(p);

    }
}