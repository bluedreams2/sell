package com.imooc.repository;

import com.imooc.dataobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by msi on 2019/7/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {


    @Autowired
    private  ProductCategoryRepository productCategoryRepository;
    @Test
    public  void findOneTest(){
        Optional<ProductCategory> productCategory = productCategoryRepository.findById(1);
        System.out.println(productCategory.get().toString());
    }

    @Test
    public  void findListTest(){

        List<ProductCategory> categoryTypeIn = productCategoryRepository.findByCategoryTypeIn(Arrays.asList(2, 3));
        System.out.println(categoryTypeIn);

    }
    @Test
    @Transactional //测试事物做完就回滚了
    public  void saveTest(){
        ProductCategory p = new ProductCategory();
        p.setCategoryName("女生2最爱");
        p.setCategoryType(2);
        p.setCategoryId(2);
        p.setCreateTime(new Date());
        p.setUpdateTime(new Date());
        productCategoryRepository.save(p);
    }
    @Test
    public  void updateTst(){
        Optional<ProductCategory> p = productCategoryRepository.findById(2);
        ProductCategory productCategory = p.get();

        productCategory.setCategoryName("女生爱");
        productCategoryRepository.save(productCategory);
    }
}