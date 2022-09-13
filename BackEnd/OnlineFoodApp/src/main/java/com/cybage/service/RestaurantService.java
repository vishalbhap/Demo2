package com.cybage.service;

import java.util.List;

import com.cybage.model.Restaurant;

public interface RestaurantService {
	public Restaurant save(Restaurant restaurant);

	public String deleteByRestaurantId(int restaurantId);

	public Restaurant updateRestaurant(Restaurant restaurant);

	public List<Restaurant> findAllRestaurants();

	public Restaurant findByRestaurantEmailAndRestaurantPassword(String email, String password);

	public Restaurant findByRestaurantId(int restaurantId);

	Restaurant findByRestaurantEmail(String email);

}
