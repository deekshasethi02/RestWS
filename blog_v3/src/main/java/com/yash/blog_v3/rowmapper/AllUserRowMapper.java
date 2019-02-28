package com.yash.blog_v3.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yash.blog_v3.domain.Blog;


public class AllUserRowMapper {

	public List<Blog> mapRow(ResultSet rs) {
		List<Blog> blogs= new ArrayList<>();
		try {
			
			while(rs.next()) {
				Blog blog = new Blog();
			    blog.setId(rs.getInt("id"));
			    blog.setTitle(rs.getString("title"));
			    blog.setBody(rs.getString("body"));
				blog.setCreated_at(rs.getString("created_at"));
				blog.setUpdated_at(rs.getString("updated_at"));
				blogs.add(blog);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return blogs;
	}

}
