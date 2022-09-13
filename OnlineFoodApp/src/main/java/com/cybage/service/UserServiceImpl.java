package com.cybage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cybage.dao.UserDao;
import com.cybage.dao.UserOrderDao;
import com.cybage.exception.CustomException;
import com.cybage.model.User;
import com.cybage.model.UserOrder;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private UserOrderDao userOrderDao;

	@Override
	public User saveUser(User user) {
		return userDao.save(user);
	}

	@Override
	public List<User> findAllUsers() {
		return userDao.findAll();
	}

	@Override
	public User findByUserEmailAndUserPassword(String email, String password) throws CustomException {
		User user = findByUserEmail(email);
		if (user == null) {
			throw new CustomException("user not found");
		}
		if (user.getUserEmail().equals(email) && user.getUserPassword().equals(password)) {
			return user;
		}
		return null;
	}

	@Override
	public User findByUserEmail(String email) {
		if (userDao.findByUserEmail(email) == null) {
			return null;
		} else {
			return userDao.findByUserEmail(email);
		}
	}

	@Override
	public List<UserOrder> findByUser(int userId) {
		User user = userDao.findById(userId).orElse(null);
		List<UserOrder> orderList = userOrderDao.findByUser(user);
		return orderList;
	}

	@Override
	public User findByUserId(int userId) {
		return userDao.findById(userId).orElse(null);

	}

	@Override
	public User saveUpdatedUser(User user, int userId) {
		User updatedUser = userDao.findById(userId).get();
//		updatedUser.setAddress(user.getAddress());
		updatedUser.setUserName(user.getUserName());
		updatedUser.setUserMobileNo(user.getUserMobileNo());
		return userDao.save(updatedUser);
	}

	@Override
	public User saveUpdatedPasswordUser(User user, int userId) {
		User updatedUser = userDao.findById(userId).get();
		updatedUser.setUserPassword(user.getUserPassword());
		return userDao.save(updatedUser);
	}

	@Override
	public User updateUser(User user) {
		return userDao.save(user);
	}

}
