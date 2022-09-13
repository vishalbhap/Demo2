package com.cybage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cybage.model.FoodMenus;

public interface FoodMenusDao extends JpaRepository<FoodMenus, Integer>{
	
	@Query(nativeQuery = true, value="select * from food_menu where restaurant_id=?1")
	List<FoodMenus> findFoodByRestaurantId(int restuarantId);
	
	List<FoodMenus> findByOfferNot(double offer);
	
	List<FoodMenus> findByOffer(double offer);

}
