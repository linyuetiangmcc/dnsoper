package com.imooc.respository;

import com.imooc.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterReposity extends JpaRepository<OrderMaster,String> {

    Page<OrderMaster> findByBuyerId(String buyerId, Pageable pageable);

}
