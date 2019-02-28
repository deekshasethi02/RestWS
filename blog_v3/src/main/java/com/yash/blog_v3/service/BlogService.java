package com.yash.blog_v3.service;

import java.util.List;

import com.yash.blog_v3.domain.Blog;



public interface BlogService {
	
	/**
	 * this method will create a blog.  
	 * @param blog object of Blog
	 */
	Blog create(Blog blog);
	
	/**
	 * this method will destroy the blog by id.
	 * @param id of blog
	 */
	Blog destroy(int id);
	
	/**
	 * this method will edit the blog.
	 * @param blog object of Blog.
	 */
	Blog edit(Blog blog);
	
	/**
	 * this method will return blog.
	 * @param id of blog
	 * @return blog object
	 */
	Blog getBlog(int id);
	
	/**
	 * this will return list of blogs.
	 * @return list of blogs
	 */
	List<Blog> listBlogs();
	
	
	
	List<Blog> deletedBlogs();
	
	 List<Integer> deletedBlogAcrMonths() ;

	List<Integer> createdBlogAcrMonths();
	
	 int deletedBlogAcrMonthsCount();
	
	 int createdBlogAcrMonthsCount();
	 public List<Blog> listAllBlogs();
	 void createBlog(Blog blog, String[] categories);
	 public List<Blog> list(int cat_id);
	 
}
