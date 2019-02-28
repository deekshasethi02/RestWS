package com.yash.restdemoapp.dao;

import java.util.List;

import com.yash.restdemoapp.model.User;

public interface UserDAO {
	
	public List<User> list();
	public User list(int id );
	public User insert(User user);
	public User update(User user);
	public User delete(int  id);

}
