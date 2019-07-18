package com.imooc.controller;

import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.ProductInfo;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductService;
import com.imooc.vo.ProductInfoVo;
import com.imooc.vo.ProductVo;
import com.imooc.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product/list")
public class BuyerProductController {


    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/list")
    public  Object list(){
        //1:查询商检
        List<ProductInfo> productInfoList = productService.findUpAll();
        //2:查询类目
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(productInfoList.stream().map(i->i.getCategoryType()).collect(Collectors.toList()));
        //3：数据瓶装
       List<ProductVo> p = new ArrayList<>();
        for(ProductCategory category:productCategoryList){
            ProductVo productVo = new ProductVo();
            productVo.setCategoryType(category.getCategoryType());
            productVo.setCategoryName(category.getCategoryName());
            List<ProductInfoVo>  productInfoVoList = new ArrayList<>();
            for(ProductInfo productInfo:productInfoList){
                if(productInfo.getCategoryType().equals(category.getCategoryType())){
                 ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo,productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
                productVo.setList(productInfoVoList);
            }
            p.add(productVo);
        }
        ResultVo<ProductVo> resultVo = new ResultVo();
        ProductVo ProductVo = new ProductVo();
        ProductInfoVo infoVo = new ProductInfoVo();
        ProductVo.setList(productInfoList);
        resultVo.setData(ProductVo);
        return resultVo;
    }

}
