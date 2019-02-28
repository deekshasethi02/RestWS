package com.yash.restapp.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import com.yash.restapp.domain.Blog;
import com.yash.restapp.service.BlogService;

public class BlogServiceImpl implements BlogService {
	
	List<Blog> blogList;
	public BlogServiceImpl() {
		blogList  = new ArrayList<>();
		blogList.add(new Blog(101, "title 1", "body 1"));
		blogList.add(new Blog(102, "title 2", "body 2"));
		blogList.add(new Blog(103, "title 3", "body 3"));
	}
	@Override
	public List<Blog> listBlogs() {
	
		return blogList;
	}
	@Override
	public Blog findbyId(int id) {
		return blogList.get(id);
	}
}
	
//SpringReactPage in visual (react page for list of blogs) deeksha/spring+react1/frontend/src/SpringReactpage