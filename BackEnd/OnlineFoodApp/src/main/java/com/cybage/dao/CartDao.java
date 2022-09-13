package com.cybage.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cybage.model.Cart;

public interface CartDao extends JpaRepository<Cart, Integer> {

}
