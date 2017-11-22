package com.imooc.service.impl;

import com.imooc.dataobject.SellerInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellerServiceImplTest {
    private static final String sellerId ="1511352531320979770";

    @Autowired
    private SellerServiceImpl sellerService;

    @Test
    public void findSellerInfoBySellerId() throws Exception {
        SellerInfo result = sellerService.findSellerInfoBySellerId(sellerId);
        Assert.assertEquals(sellerId,result.getSellerId());

    }

}