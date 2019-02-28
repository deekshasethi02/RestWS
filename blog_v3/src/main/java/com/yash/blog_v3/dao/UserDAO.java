package com.yash.blog_v3.dao;

import java.util.List;

import com.yash.blog_v3.domain.User;

public interface UserDAO {

		/**
		 * This method will insert the user in database.
		 * @param user
		 */
		User insert(User user);
		
		/**
		 * It will return the found user of the given id.
		 * @param id of user.
		 * @return user if found otherwise null.
		 */
		User show(int id);

		/**
		 * It will update the user detail.
		 * @param user
		 */
		User update(User user);
		
		/**
		 * This method will delete the user of specified id.
		 * @param id
		 */
		User delete(int id);
		
		/**
		 * This method will return the list of user if found.
		 * @return list of user if found else null.
		 */
		List<User> list();
		
		
}
