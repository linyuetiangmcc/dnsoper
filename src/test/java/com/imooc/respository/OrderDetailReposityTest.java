package com.imooc.respository;

import com.imooc.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailReposityTest {

    @Autowired
    private OrderDetailReposity reposity;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123456");
        orderDetail.setOrderId("123456");
        orderDetail.setProductIcon("http://xxx.jpg");
        orderDetail.setProductId("11112");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(1.2));
        orderDetail.setProductQuantity(2);

        OrderDetail result = reposity.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderIdTest() throws Exception {
        List<OrderDetail> orderDetailList = reposity.findByOrderId("123456");

        Assert.assertNotEquals(0,orderDetailList.size());
    }

}