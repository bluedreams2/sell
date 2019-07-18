package com.imooc.repository;

import com.imooc.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by msi on 2019/7/14.
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

        List<OrderDetail> findByOrderId(String orderId);

}
