package com.cybage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cybage.model.Address;
import com.cybage.model.Restaurant;

public interface RestaurantDao extends JpaRepository<Restaurant, Integer>{
	List<Restaurant> findByAddress(Address address);

	Restaurant findByRestaurantEmailAndRestaurantPassword(String email,String password);
	
	Restaurant findByRestaurantEmail(String email);
}
