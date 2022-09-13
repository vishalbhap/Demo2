package com.cybage.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.dao.OrderInfoDao;
import com.cybage.dao.UserOrderDao;
import com.cybage.model.Restaurant;
import com.cybage.model.User;
import com.cybage.model.UserOrder;

@Service
@Transactional
public class UserOrderServiceImpl implements UserOrderService {

	@Autowired
	UserOrderDao userOrderDao;

	@Autowired
	OrderInfoDao orderInfoDao;

	@Override
	public UserOrder save(UserOrder userOrder, double total, Restaurant restaurant, User user) {
		userOrder.setOrderStatus("placed");
		userOrder.setTotal(total);
		userOrder.setRestaurant(restaurant);
		userOrder.setUser(user);
		LocalDateTime dateTime = LocalDateTime.now();
		userOrder.setOrderTime(dateTime);

		return userOrderDao.save(userOrder);
	}

	@Override
	public List<UserOrder> getAllOrders(Restaurant restaurant) {
		return userOrderDao.findByRestaurant(restaurant);
	}

	@Override
	public List<UserOrder> getAllOrderForUser(User user) {
		return userOrderDao.findByUser(user);
	}

	@Override
	public UserOrder findUserOrderByOrderId(int orderId) {
		return userOrderDao.findById(orderId).orElse(null);
	}

	@Override
	public void save(UserOrder userOrder) {
		userOrderDao.save(userOrder);

	}

	@Override
	public UserOrder saveOrderSimple(UserOrder userOrder) {
		userOrder.setOrderStatus("placed");
		LocalDateTime dateTime = LocalDateTime.now();
		userOrder.setOrderTime(dateTime);
		return userOrderDao.save(userOrder);
	}

}
