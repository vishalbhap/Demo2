package com.cybage.service;

import java.util.List;

import com.cybage.exception.CustomException;
import com.cybage.model.User;
import com.cybage.model.UserOrder;

public interface UserService {
	public User saveUser(User user);

	public List<User> findAllUsers();

	public User findByUserEmailAndUserPassword(String email, String password) throws CustomException;

	public User findByUserEmail(String email);

	public List<UserOrder> findByUser(int userId);

	public User findByUserId(int userId);

	public User saveUpdatedUser(User user, int userId);

	public User saveUpdatedPasswordUser(User user, int userId);

	public User updateUser(User user);

}
