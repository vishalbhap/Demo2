package com.cybage.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cybage.model.User;

public interface UserDao extends JpaRepository<User, Integer>{
	User findByUserEmailAndUserPassword(String email,String password);

	User findByUserEmail(String email);
	
	
}
