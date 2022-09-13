package com.cybage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cybage.model.OrderInfo;
import com.cybage.model.UserOrder;

public interface OrderInfoDao extends JpaRepository<OrderInfo, Integer>{

	List<OrderInfo> findByUserOrder(UserOrder order);


}
