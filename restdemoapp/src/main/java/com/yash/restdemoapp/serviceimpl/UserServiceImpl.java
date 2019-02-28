package com.yash.restdemoapp.serviceimpl;

import java.util.List;

import com.yash.restdemoapp.dao.UserDAO;
import com.yash.restdemoapp.daoimpl.UserDAOImpl;
import com.yash.restdemoapp.model.User;
import com.yash.restdemoapp.service.UserService;

public class UserServiceImpl implements UserService {

UserDAO userDAO =  new UserDAOImpl();
	
	@Override
	public List<User> getAllUsers() {
		
		return userDAO.list();
	}

	@Override
	public User findById(int id) {
		
		return userDAO.list(id);
	}

	@Override
	public User addUser(User user) {
		
		return userDAO.insert(user);
	}

	@Override
	public User updateUser(User user) {
		
		return userDAO.update(user);
	}

	@Override
	public User deleteUser(int id) {
		
		return userDAO.delete(id);
	}

}
