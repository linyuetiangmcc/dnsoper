package com.imooc.service.impl;

import com.imooc.dataobject.SellerInfo;
import com.imooc.respository.SellerInfoRepository;
import com.imooc.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoBySellerId(String sellerId) {
        return repository.findBySellerId(sellerId);
    }

    @Override
    public SellerInfo findSellerInfoByUsername(String username) {
        return repository.findByUsername(username);
    }
}
