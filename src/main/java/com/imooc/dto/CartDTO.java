package com.imooc.dto;

import lombok.Data;

@Data
public class CartDTO {


    private  String productId;

    private  Integer productQuanity;


    public CartDTO(String productId, Integer productQuanity) {
        this.productId = productId;
        this.productQuanity = productQuanity;
    }


}
