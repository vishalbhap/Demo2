package com.cybage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cybage.dao.RestaurantDao;
import com.cybage.model.Restaurant;
import com.cybage.utility.StorageService;

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {
	@Autowired
	private RestaurantDao restaurantDao;

	@Autowired
	private StorageService storageService;

	@Override
	public Restaurant save(Restaurant restaurant) {

		return restaurantDao.save(restaurant);
	}

	@Override
	public String deleteByRestaurantId(int restaurantId) {
		restaurantDao.deleteById(restaurantId);
		return "Restaurant deleted successfully";
	}

	@Override
	public Restaurant updateRestaurant(Restaurant restaurant) {
		return restaurantDao.save(restaurant);
	}

	@Override
	public List<Restaurant> findAllRestaurants() {
		return restaurantDao.findAll();
	}

	@Override
	public Restaurant findByRestaurantEmailAndRestaurantPassword(String email, String password) {
		return restaurantDao.findByRestaurantEmailAndRestaurantPassword(email, password);
	}

	@Override
	public Restaurant findByRestaurantId(int restaurantId) {
		return restaurantDao.findById(restaurantId).get();
	}

	@Override
	public Restaurant findByRestaurantEmail(String email) {
		return restaurantDao.findByRestaurantEmail(email);
	}

}
