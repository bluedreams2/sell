package com.imooc.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 商品 包含类名
 */
@Data
public class ProductVo<T> {

    @JsonProperty("name")
    private    String categoryName;

    @JsonProperty("type")
    private  Integer categoryType;

    @JsonProperty("food")
    private List<T> list;



}
