package com.imooc.respository;

import com.imooc.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterReposityTest {

    @Autowired OrderMasterReposity reposity;

    private final String BUYERID = "110";

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123457");
        orderMaster.setBuyerName("师兄");
        orderMaster.setBuyerPhone("12345678901");
        orderMaster.setBuyerAddress("room 1");
        orderMaster.setBuyerId("110");
        orderMaster.setOrderAmount(new BigDecimal(2.5));

        OrderMaster result =  reposity.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerIdTest() throws Exception {
        PageRequest request = new PageRequest(0,2);

        Page<OrderMaster> result = reposity.findByBuyerId(BUYERID,request);
        Assert.assertNotEquals(0,result.getTotalElements());
    }

}