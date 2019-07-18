package com.imooc.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by msi on 2019/7/14.
 */
@Data
public class ProductInfoVo {



    @JsonProperty("id")
    private     String productId;

    @JsonProperty("name")
    private  String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private  String producrDescription;

    @JsonProperty("icon")
    private  String productIcon;

}
