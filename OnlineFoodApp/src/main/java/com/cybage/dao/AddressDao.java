package com.cybage.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cybage.model.Address;

public interface AddressDao extends JpaRepository<Address, Integer> {
	Address findByArea(String searchByArea);
}
