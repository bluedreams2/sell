package com.imooc.converter;

import com.imooc.dataobject.OrderMaster;
import com.imooc.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by msi on 2019/7/15.
 */
public class OrderMaster2OrderDTO {

    public  static OrderDTO convert(OrderMaster orderMaster){

       OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);

        return  orderDTO;
    }

    public  static List<OrderDTO> convert(List<OrderMaster> orderMasters){
      return  orderMasters.stream().map(e->convert(e))
                .collect(Collectors.toList());
    }

}
