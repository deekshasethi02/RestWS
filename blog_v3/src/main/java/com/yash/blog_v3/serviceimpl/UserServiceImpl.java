package com.yash.blog_v3.serviceimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.yash.blog_v3.dao.UserDAO;
import com.yash.blog_v3.daoimpl.UserDAOImpl;
import com.yash.blog_v3.domain.User;
import com.yash.blog_v3.service.UserService;
import com.yash.blog_v3.util.JNDIConnectionPooling;
import com.yash.blog_v3.util.SimpleJDBCUtil;



public class UserServiceImpl extends JNDIConnectionPooling implements UserService {
	 
	UserDAO userDAO = null;
	public UserServiceImpl() {
		userDAO = new UserDAOImpl();
	}
	@Override
	public User create(User user) {
		 user = userDAO.insert(user);
		return user;   
	}

	@Override
	public User destroy(int id) {
		return userDAO.delete(id);
	}

	@Override
	public User edit(User user) {
		user = userDAO.update(user);
		return user;
	}

	@Override
	public User getUser(int id) {
		return userDAO.show(id);
	}

	@Override
	public List<User> listUsers() {
		return userDAO.list();
	}
	@Override
	public User authenticateUser(String email, String password) {
		User user = new User();
		
		try {
			String query = "SELECT * FROM users where email =? AND password=?";
			PreparedStatement pstmt = createPreparedStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			ResultSet rs =pstmt.executeQuery();
			if (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setContact(rs.getLong("contact"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getInt("role"));
				user.setStatus(rs.getInt("status"));
				user.setCreated_at(rs.getString("created_at"));
				user.setUpdated_at(rs.getString("updated_at"));
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	
	}
	
}
