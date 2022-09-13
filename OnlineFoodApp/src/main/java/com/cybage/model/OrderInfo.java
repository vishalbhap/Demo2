package com.cybage.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "order_info")
public class OrderInfo {
	@Id
	@GeneratedValue
	private int srNo;

	@ManyToOne
	@JoinColumn(name = "food_id")
	private FoodMenus foodMenus;
	private int foodRating;
	private String feedback;
	private int quantity;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "order_id")
	private UserOrder userOrder;

	public OrderInfo() {

	}

	public OrderInfo(int srNo, FoodMenus foodMenus, int foodRating, String feedback, int quantity,
			UserOrder userOrder) {
		this.srNo = srNo;
		this.foodMenus = foodMenus;
		this.foodRating = foodRating;
		this.feedback = feedback;
		this.quantity = quantity;
		this.userOrder = userOrder;
	}

	public int getSrNo() {
		return srNo;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}

	public FoodMenus getFoodMenus() {
		return foodMenus;
	}

	public void setFoodMenus(FoodMenus foodMenus) {
		this.foodMenus = foodMenus;
	}

	public int getFoodRating() {
		return foodRating;
	}

	public void setFoodRating(int foodRating) {
		this.foodRating = foodRating;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public UserOrder getUserOrder() {
		return userOrder;
	}

	public void setUserOrder(UserOrder userOrder) {
		this.userOrder = userOrder;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderInfo [srNo=" + srNo + ", foodMenus=" + foodMenus + ", foodRating=" + foodRating + ", feedback="
				+ feedback + ", quantity=" + quantity + "]";
	}

	
}
