package com.cybage.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {
	@Id
	private int cartId;
	private int userId; 
	private int restId;

	public Cart() {
//		this.foodItems=new ArrayList<>();
	}
	public Cart(int cartId, int userId, int restId, List<FoodMenus> foodItems) {
		this.cartId = cartId;
		this.userId = userId;
		this.restId = restId;
//		this.foodItems = foodItems;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRestId() {
		return restId;
	}
	public void setRestId(int restId) {
		this.restId = restId;
	}

	
}
