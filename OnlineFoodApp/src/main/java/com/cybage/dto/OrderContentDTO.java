package com.cybage.dto;

import java.util.ArrayList;
import java.util.List;

import com.cybage.model.FoodMenus;
import com.cybage.model.User;

public class OrderContentDTO {
    private List<FoodMenus> foodMenus;
    private User user;
    private double total;

    public OrderContentDTO() {
        this.foodMenus = new ArrayList<FoodMenus>();
    }

    public OrderContentDTO(List<FoodMenus> foodMenus, User user, double total) {
        this.foodMenus = foodMenus;
        this.user = user;
        this.total = total;
    }

    public List<FoodMenus> getFoodMenus() {
        return foodMenus;
    }

    public void setFoodMenus(List<FoodMenus> foodMenus) {
        this.foodMenus = foodMenus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }   
    
}
