package com.yash.restdemoapp.daoimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yash.restdemoapp.dao.UserDAO;
import com.yash.restdemoapp.model.User;

public class UserDAOImpl implements UserDAO {

private static Map<Integer, User> usersMap =  new HashMap<>();
	
	
	public UserDAOImpl() {
		usersMap.put(101, new User(101, "Pankaj Sharma", "pankaj@gmail.com"));
		usersMap.put(102, new User(102, "Priyansh Sharma", "priyansh@gmail.com"));
		usersMap.put(103, new User(103, "Manvi Sharma", "manvi@gmail.com"));
	}

	@Override
	public List<User> list() {
		List<User> userList =  new ArrayList<>(usersMap.values());
		return userList;
	}

	@Override
	public User list(int id) {
		User user = usersMap.get(id);
		return user;
	}

	@Override
	public User insert(User user) {
		usersMap.put(user.getId(), user);
		System.out.println(usersMap);
		return usersMap.get(user.getId());
	}

	@Override
	public User update(User user) {
		if(usersMap.get(user.getId())!=null){
			usersMap.get(user.getId()).setName(user.getName());
			usersMap.get(user.getId()).setEmail(user.getEmail());
		}
		else{
			usersMap.put(user.getId(), user);
		}
		return usersMap.get(user.getId());
	}

	@Override
	public User delete(int id) {
		User user = usersMap.remove(id);
		return user;
	}

}
