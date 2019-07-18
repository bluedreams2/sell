package com.imooc.service;

import com.imooc.dataobject.ProductCategory;

import java.util.List;

/**
 * Created by msi on 2019/7/14.
 */
public interface CategoryService {

     ProductCategory findOne(Integer cid);


     List<ProductCategory> findAll();

     List<ProductCategory> findByCategoryTypeIn(List<Integer> typeList);

     ProductCategory save(ProductCategory productCategory);
    }

