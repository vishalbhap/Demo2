package com.cybage.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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

@Entity
@Table(name = "restaurant")
public class Restaurant {
	@Id
	@GeneratedValue
	private int restId;
	private String restaurantName;
	private String restaurantUserName;
	private String restaurantPassword;
	private String restaurantEmail;
	private String thumbnail;
	private int attemptCount;
	private String distance;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;

	@JsonIgnore
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
	private List<FoodMenus> foodMenus;



	@JsonIgnore
	@OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
	private List<UserOrder> userOrder;

	

	public Restaurant() {
		this.foodMenus = new ArrayList<>();
		this.userOrder = new ArrayList<>();
	}

	public Restaurant(int restId, String restaurantName, String restaurantUserName, String restaurantPassword,
			String restaurantEmail, String thumbnail, int attemptCount, String distance, Address address,
			List<FoodMenus> foodMenus, List<UserOrder> userOrder) {
		this.restId = restId;
		this.restaurantName = restaurantName;
		this.restaurantUserName = restaurantUserName;
		this.restaurantPassword = restaurantPassword;
		this.restaurantEmail = restaurantEmail;
		this.thumbnail = thumbnail;
		this.attemptCount = attemptCount;
		this.distance = distance;
		this.address = address;
		this.foodMenus = foodMenus;
		this.userOrder = userOrder;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public int getRestId() {
		return restId;
	}

	public void setRestId(int restId) {
		this.restId = restId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getRestaurantUserName() {
		return restaurantUserName;
	}

	public void setRestaurantUserName(String restaurantUserName) {
		this.restaurantUserName = restaurantUserName;
	}

	public String getRestaurantPassword() {
		return restaurantPassword;
	}

	public void setRestaurantPassword(String restaurantPassword) {
		this.restaurantPassword = restaurantPassword;
	}

	public String getRestaurantEmail() {
		return restaurantEmail;
	}

	public void setRestaurantEmail(String restaurantEmail) {
		this.restaurantEmail = restaurantEmail;
	}

	public int getAttemptCount() {
		return attemptCount;
	}

	public void setAttemptCount(int attemptCount) {
		this.attemptCount = attemptCount;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<FoodMenus> getFoodMenus() {
		return foodMenus;
	}

	public void setFoodMenus(List<FoodMenus> foodMenus) {
		this.foodMenus = foodMenus;
	}

	public List<UserOrder> getUserOrder() {
		return userOrder;
	}

	public void setUserOrder(List<UserOrder> userOrder) {
		this.userOrder = userOrder;
	}

	

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	@Override
	public String toString() {
		return "Restaurant [restId=" + restId + ", restaurantName=" + restaurantName + ", restaurantUserName="
				+ restaurantUserName + ", restaurantPassword=" + restaurantPassword + ", restaurantEmail="
				+ restaurantEmail + ", thumbnail=" + thumbnail + ", attemptCount=" + attemptCount + ", distance="
				+ distance + "]";
	}
	
}
