package com.imooc.service;

import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {


    /**
     * 在架商品；列表
     * @return
     */
    List<ProductInfo> findUpAll();
    Page<ProductInfo> findAll(Pageable pageable);

    //加库存
    void increaseStock(List<CartDTO> dto);



    //减库存
    void dereaseStock(List<CartDTO> dto);



    ProductInfo save(ProductInfo productCategory);



    ProductInfo findOne(String cid);




    List<ProductInfo> findByProductStatus(List<Integer> typeList);



}
