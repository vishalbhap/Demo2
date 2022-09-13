package com.cybage.dao;

import com.cybage.model.Restaurant;
import com.cybage.model.User;
import com.cybage.model.UserOrder;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOrderDao extends JpaRepository<UserOrder, Integer> {
    
	List<UserOrder> findByRestaurant(Restaurant restaurant);

	List<UserOrder> findByUser(User user);
	
	
	
	
}
