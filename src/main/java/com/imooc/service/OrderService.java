package com.imooc.service;

import com.imooc.dataobject.OrderMaster;
import com.imooc.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    //new

    OrderDTO create(OrderDTO orderDTO);

    //query
    OrderDTO findOne(String orderId);

    //query all by buyerid
    Page<OrderDTO> findList(String buyerId, Pageable pageable);

    //cancel order
    OrderDTO cancel(OrderDTO orderDTO);

    //finish order
    OrderDTO finish(OrderDTO orderDTO);

    //pay order
    OrderDTO paid(OrderDTO orderDTO);

    //query all
    Page<OrderDTO> findList(Pageable pageable);

}
