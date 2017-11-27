package com.imooc.respository;

import com.imooc.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoRepository extends JpaRepository<SellerInfo,String>{
    SellerInfo findBySellerId(String sellerId);

    SellerInfo findByUsername(String username);
}
