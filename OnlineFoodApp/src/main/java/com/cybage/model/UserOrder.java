package com.cybage.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_orders")
public class UserOrder {
	@Id
	@GeneratedValue
	private int orderId;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	private String orderStatus;
	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;

	private double total;
	@DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss")
	// @Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime orderTime;

	@JsonIgnore
	@OneToMany(mappedBy = "userOrder")
	private List<OrderInfo> orderInfo;


	
	
	public UserOrder() {
		this.orderInfo = new ArrayList<>();
	}


	
	public UserOrder(int orderId, User user, String orderStatus, Restaurant restaurant, double total,
			LocalDateTime orderTime, List<OrderInfo> orderInfo) {
		this.orderId = orderId;
		this.user = user;
		this.orderStatus = orderStatus;
		this.restaurant = restaurant;
		this.total = total;
		this.orderTime = orderTime;
		this.orderInfo = orderInfo;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public LocalDateTime getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(LocalDateTime orderTime) {
		this.orderTime = orderTime;
	}

	public List<OrderInfo> getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(List<OrderInfo> orderInfo) {
		this.orderInfo = orderInfo;
	}


	
	@Override
	public String toString() {
		return "UserOrder [orderId=" + orderId + ", user=" + user + ", orderStatus=" + orderStatus + ", restaurant="
				+ restaurant + ", total=" + total + ", orderTime=" + orderTime + ", orderInfo=" + orderInfo
				+ "]";
	}


	

}
