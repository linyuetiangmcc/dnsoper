package com.imooc.service.impl;

import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.BuyerService;
import com.imooc.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String buyerid, String orderId) {
        return checkOrderOwner(buyerid,orderId);
    }

    @Override
    public OrderDTO cancelOrder(String buyerid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(buyerid,orderId);
        if(orderDTO == null){
            log.error("[取消订单]查询不到订单，orderId={}",orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String buyerid, String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO == null){
            return null;
        }
        if(!orderDTO.getBuyerId().equalsIgnoreCase(buyerid)){
            log.error("[查询订单]订单的buyerid不一致，buyerid={},orderDTO={}",buyerid,orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
