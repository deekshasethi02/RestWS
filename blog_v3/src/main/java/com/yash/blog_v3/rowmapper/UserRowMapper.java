package com.yash.blog_v3.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.yash.blog_v3.domain.User;


public class UserRowMapper {
	public User mapRow(ResultSet rs, User user) {
		try {
			if (rs.next()) {
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setContact(rs.getLong("contact"));	
				user.setEmail(rs.getString("email"));
				user.setCreated_at(rs.getString("created_at"));
				user.setUpdated_at(rs.getString("updated_at"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return user;
	}
}
