package com.imooc.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imooc.dataobject.OrderDetail;
import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by msi on 2019/7/18.
 */
@Slf4j
public class OrderForm2OrderDTOConverter {


    public  static OrderDTO converter(OrderForm orderForm){

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        //JSON-->LIST
        try {
            Gson gson = new Gson();
            orderDTO.setOrderDetailList(gson.fromJson(orderForm.getItems(),
                    new TypeToken<OrderDetail>() {
                    }.getType()));
        }
        catch (Exception e){
            log.error("【对象转换】错误,json={}",orderForm.getItems());
            throw  new SellException(ResultEnum.PARAM_ERROR);
        }

        return  orderDTO;

    }
}
