package com.imooc.service.impl;


import com.imooc.converter.OrderMaster2OrderDTO;
import com.imooc.dataobject.OrderDetail;
import com.imooc.dataobject.OrderMaster;
import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.CartDTO;
import com.imooc.dto.OrderDTO;
import com.imooc.enums.OrderStatus;
import com.imooc.enums.PayStatus;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.repository.OrderDetailRepository;
import com.imooc.repository.OrderMasterResipotory;
import com.imooc.service.OrderService;
import com.imooc.service.ProductService;
import com.imooc.utils.KeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private ProductService productService;
    @Autowired
    private OrderMasterResipotory masterResipotory;
    @Autowired
    private OrderDetailRepository detailRepository;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderMaster) {
        String orderId=KeyUtils.genUniqueKey();
        String detailId = KeyUtils.genUniqueKey();
        List<CartDTO> cartDTOList = new ArrayList<>();
        //1:查询商品 数量 价格
        BigDecimal orderAmount = new BigDecimal(0);
        List<OrderDetail> orderDetailList = orderMaster.getOrderDetailList();
    for(OrderDetail orderDetail:orderDetailList){
        ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        //2:计算总价 原来的总价加上当前商品的总价
     orderAmount= orderDetail.getProductPrice().
             multiply(new BigDecimal(orderDetail.getProductQuantity()))
             .add(orderAmount);
        //3:详情入库
        orderDetail.setDetailId(detailId);
        orderDetail.setOrderId(orderId);
        //3.1 生成cartDTO
        //        cartDTOList.add( new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity()));
        BeanUtils.copyProperties(orderDetail,productInfo);
        detailRepository.save(orderDetail);
    }
        //3:写入订单数据库 orderMaster orderDeatil
        OrderMaster master = new OrderMaster();
        //属性拷贝null 也会拷贝
        BeanUtils.copyProperties(orderMaster,master);
        master.setOrderId(orderId);
        master.setOrderAmount(orderAmount);
        masterResipotory.save(master);
        //4:扣库存
        orderMaster.getOrderDetailList()
                .stream().map(i->{
            CartDTO cartDTO = new CartDTO(i.getProductId(),i.getProductQuantity());
            return cartDTO;
        }).collect(Collectors.toList());
        productService.dereaseStock(cartDTOList);

        return orderMaster;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = masterResipotory.findById(orderId).get();
        boolean b = orderMaster == null;
        if(b){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = detailRepository.findByOrderId(orderMaster.getOrderId());
        if(null == orderDetailList){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return  orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {

        Page<OrderMaster> page = masterResipotory.findByBuyerOpenid(buyerOpenid, pageable);

         List<OrderDTO> orderDTOList = OrderMaster2OrderDTO.convert(page.getContent());
        
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList,
                pageable,page.getTotalElements());

        return  orderDTOPage;

    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        //对象拷贝null会覆盖
        BeanUtils.copyProperties(orderDTO,orderMaster);
        //判断订单状态
        if(orderDTO.getOrderStatus().equals(OrderStatus.NEW.getCode())){
            log.error("【取消订单】 订单状态不正确,orrerId={}.orderStatus={}",
                    orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new  SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderMaster.setOrderStatus(OrderStatus.CANCEL.getCode());
        OrderMaster updateResult = masterResipotory.save(orderMaster);

        if(updateResult == null){
            log.error("【取消订单】更新失败,orderMaster={}",orderMaster);
            throw new  SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("[取消订单]订单中午商品详情为空,orderDto={}",orderDTO);
        }
        //返还库存
        List<CartDTO> cartDTOList  = orderDTO.getOrderDetailList().stream()
                .map(e->new CartDTO(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
        productService.increaseStock(cartDTOList);
        //退款，通知用户
        if(orderDTO.getPayStatus().equals(PayStatus.PAYSUCCESS.getCode())){
            //TODO
        }
        return orderDTO;
    }

    //订单完结
    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if(orderDTO.getOrderStatus().equals(OrderStatus.NEW.getCode())){
            log.error("【完结订单】订单状态不正确，orderId={}.orderStatus={}",
                    orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new  SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改状态
        orderDTO.setOrderStatus(OrderStatus.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster save = masterResipotory.save(orderMaster);
        if(save == null){
            log.error("【完结订单】更新失败,orderMaster={}",orderMaster);
            throw new  SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }
    //支付
    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if(orderDTO.getOrderStatus().equals(OrderStatus.NEW.getCode())){
            log.error("【订单支付成功】订单状态不正确，orderId={}.orderStatus={}",
                    orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new  SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if(!orderDTO.getPayStatus().equals(PayStatus.WAITEPAY.getCode())){
            log.error("【订单支付成功】订单支付状态不正确，orderId={}.orderStatus={}",
                    orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new  SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单支付状态
        orderDTO.setPayStatus(PayStatus.WAITEPAY.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster update = masterResipotory.save(orderMaster);
        if(update==null){
            log.error("【订单支付转台完成】更新失败,orderMaster={}",orderMaster);
            throw  new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }



        return orderDTO;
    }
}
