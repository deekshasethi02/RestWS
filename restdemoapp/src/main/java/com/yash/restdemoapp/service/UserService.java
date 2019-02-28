package com.yash.restdemoapp.service;

import java.util.List;

import com.yash.restdemoapp.model.User;

public interface UserService {

public List<User> getAllUsers();
	
	public User findById(int id);
	
	public User addUser(User user);
	
	public User updateUser(User user);
	
	public User deleteUser(int id);
}
