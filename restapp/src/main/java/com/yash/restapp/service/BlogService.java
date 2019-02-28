package com.yash.restapp.service;

import java.util.List;

import com.yash.restapp.domain.Blog;

public interface BlogService {
	
	public List<Blog> listBlogs();
	
	public Blog findbyId(int id);
}
