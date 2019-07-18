package com.imooc.repository;

import com.imooc.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by msi on 2019/7/14.
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {




    List<ProductCategory> findByCategoryTypeIn(List<Integer> typeList);

}
