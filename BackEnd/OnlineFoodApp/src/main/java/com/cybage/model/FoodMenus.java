package com.cybage.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "food_menu")
public class FoodMenus {
	@Id
	@GeneratedValue
	private int foodId;
	private String foodName;
	private double price;
	private double offer;
	private String foodCategory;
	private String thumbnail;  
	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;

	@Transient
	private int quantity;

	@JsonIgnore
	@OneToMany(mappedBy = "foodMenus")
	private List<OrderInfo> orderInfo;

	public FoodMenus() {
		this.orderInfo = new ArrayList<>();
	}

	public FoodMenus(int foodId, String foodName, double price, double offer, String foodCategory, String thumbnail,
			Restaurant restaurant, int quantity, List<OrderInfo> orderInfo) {
		this.foodId = foodId;
		this.foodName = foodName;
		this.price = price;
		this.offer = offer;
		this.foodCategory = foodCategory;
		this.thumbnail = thumbnail;
		this.restaurant = restaurant;
		this.quantity = quantity;
		this.orderInfo = orderInfo;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getOffer() {
		return offer;
	}

	public void setOffer(double offer) {
		this.offer = offer;
	}

	public String getFoodCategory() {
		return foodCategory;
	}

	public void setFoodCategory(String foodCategory) {
		this.foodCategory = foodCategory;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public List<OrderInfo> getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(List<OrderInfo> orderInfo) {
		this.orderInfo = orderInfo;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "FoodMenus [foodId=" + foodId + ", foodName=" + foodName + ", price=" + price + ", offer=" + offer
				+ ", foodCategory=" + foodCategory + ", thumbnail=" + thumbnail + ", restaurant=" + restaurant
				+ ", quantity=" + quantity + "]";
	}

}
