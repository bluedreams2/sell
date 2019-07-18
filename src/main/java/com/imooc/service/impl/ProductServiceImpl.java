package com.imooc.service.impl;

import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.CartDTO;
import com.imooc.enums.ProductStatus;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.repository.ProductInfoRepository;
import com.imooc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductInfoRepository repository;
    @Override
    public ProductInfo findOne(String id) {
        return repository.findById(id).get();
    }




    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatus.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> dto) {
        for(CartDTO cartDTO:dto){
            Optional<ProductInfo> o  = repository.findById(cartDTO.getProductId());
            ProductInfo productInfo = o.get();
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //增加库存
            Integer result = productInfo.getProductStock()
                    +cartDTO.getProductQuanity();
            productInfo.setProductStock(result);
            repository.save(productInfo);

        }

    }

    @Override
    @Transactional
    public void dereaseStock(List<CartDTO> dto) {
        for(CartDTO cartDTO:dto){
            Optional<ProductInfo> o = repository.findById(cartDTO.getProductId());
            ProductInfo productInfo = o.get();
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //剩余库存《0
            Integer result = productInfo.getProductStock()-cartDTO.getProductQuanity();
            if (result <0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }


    }

    @Override
    public ProductInfo save(ProductInfo productCategory) {
        return repository.save(productCategory);
    }


    @Override
    public List<ProductInfo> findByProductStatus(List<Integer> typeList) {
        return repository.findByProductStatus(ProductStatus.UP.getCode());
    }
}
