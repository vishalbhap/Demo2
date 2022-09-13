package com.cybage.service;

import java.util.List;

import com.cybage.model.Restaurant;
import com.cybage.model.User;
import com.cybage.model.UserOrder;

public interface UserOrderService {

    public UserOrder save(UserOrder userOrder, double total, Restaurant restaurant, User user);

	public List<UserOrder> getAllOrders(Restaurant restaurant);

	public List<UserOrder> getAllOrderForUser(User user);

	public UserOrder findUserOrderByOrderId(int orderId);

	public void save(UserOrder userOrder);
	
	public UserOrder saveOrderSimple(UserOrder userOrder);


    
}
