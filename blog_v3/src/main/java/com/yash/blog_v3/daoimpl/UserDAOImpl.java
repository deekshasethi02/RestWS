package com.yash.blog_v3.daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yash.blog_v3.dao.UserDAO;
import com.yash.blog_v3.domain.User;
import com.yash.blog_v3.rowmapper.UserRowMapper;
import com.yash.blog_v3.util.DateUtil;
import com.yash.blog_v3.util.JNDIConnectionPooling;
import com.yash.blog_v3.util.SimpleJDBCUtil;

public class UserDAOImpl extends JNDIConnectionPooling implements UserDAO{
	
		@Override
		public User insert(User user) {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String created_at = new DateUtil().convertDateToString(formatter,new Date());
			String updated_at = new DateUtil().convertDateToString(formatter,new Date());
			
			String query = "INSERT INTO users (firstName, lastName, contact, email,password,created_at, updated_at) values('"
					+ user.getFirstName() + "','" + user.getLastName() + "','" + user.getContact() + "','" + user.getEmail()
					+"','" + user.getPassword()+ "','" + created_at + "','" + updated_at + "')";
			try {
				createPreparedStatement(query).execute();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return user;
			
		}

		@Override
		public User show(int id) {
			User user = new User();
			try {
				ResultSet rs = createPreparedStatement("select * from users where id=" + id).executeQuery();
				UserRowMapper userRowMapper = new UserRowMapper();
				user = userRowMapper.mapRow(rs, user);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return user;

		}

		@Override
		public User update(User user) {
			String query = "UPDATE users set firstName=?,lastName=?,contact=? where id=?";
			PreparedStatement pstmt = createPreparedStatement(query);
			try {
				pstmt.setString(1, user.getFirstName());
				pstmt.setString(2, user.getLastName());
				pstmt.setLong(3, user.getContact());
				pstmt.setInt(4, user.getId());
				pstmt.execute();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			return user;
		}

		@Override
		public User delete(int id) {
			String query = "DELETE from users where id=" + id;
			User user = show(id);
			try {
					createPreparedStatement(query).execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return user;
		}

		@Override
		public List<User> list() {
			List<User> users = new ArrayList<>();
			try {
				ResultSet rs = createPreparedStatement("select * from users").executeQuery();
				while(rs.next()) {
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setFirstName(rs.getString("firstName"));
					user.setLastName(rs.getString("lastName"));
					user.setContact(rs.getLong("contact"));
					user.setEmail(rs.getString("email"));
					user.setPassword(rs.getString("password"));
					user.setRole(rs.getInt("role"));
					user.setStatus(rs.getInt("status"));
					
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					
					String created_at = new DateUtil().convertDateToString(formatter,new Date());
					String updated_at = new DateUtil().convertDateToString(formatter,new Date());
					user.setCreated_at(created_at);
					user.setUpdated_at(updated_at);
					users.add(user);
					
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			return users;
		}

	}


