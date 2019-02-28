package com.yash.blog_v3.serviceimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yash.blog_v3.dao.BlogDAO;
import com.yash.blog_v3.daoimpl.BlogDAOImpl;
import com.yash.blog_v3.domain.Blog;
import com.yash.blog_v3.service.BlogService;
import com.yash.blog_v3.util.JNDIConnectionPooling;
import com.yash.blog_v3.util.SimpleJDBCUtil;



public class BlogServiceImpl extends JNDIConnectionPooling implements BlogService {

	BlogDAO blogDAO = null;
	
	public BlogServiceImpl() {
		blogDAO=new BlogDAOImpl(); //dependency injection
	}
	@Override
	public Blog create(Blog blog) {
		
	return	blogDAO.insert(blog);

	}
	@Override
	public Blog destroy(int id) {
		
		return blogDAO.delete(id);
		
	}
	@Override
	public Blog edit(Blog blog) {
		
		 return blogDAO.update(blog);
		
	}
	@Override
	public Blog getBlog(int id) {
		
		return blogDAO.show(id);
	}
	@Override
	public List<Blog> listBlogs() {
		
		return blogDAO.list();
	}
	
	@Override
	public List<Blog> deletedBlogs() {
		List<Blog> list = blogDAO.deletedBlog();
		return list;
	}
	
	
	
	@Override
	public List<Integer> deletedBlogAcrMonths() {
		List<Integer> list = blogDAO.deletedBlogAcrMonths();
		return list;
	}

	@Override
	public List createdBlogAcrMonths() {
		List<Integer> createdBlogAcorMonths = blogDAO.createdBlogAcrMonths();
		return createdBlogAcorMonths;
	}

	@Override
	public int deletedBlogAcrMonthsCount() {

		return blogDAO.deletedBlogAcrMonthsCount();
	}

	@Override
	public int createdBlogAcrMonthsCount() {
	
		return blogDAO.createdBlogAcrMonthsCount();
	}
	@Override
	public List<Blog> listAllBlogs() {
		
		return blogDAO.listAllBlogs();
	}
	@Override
	public void createBlog(Blog blog, String[] categories) {
		blogDAO.createBlog(blog,categories);
		
	}
	 public List<Blog> list(int cat_id){
		 
		 List<Blog> blogs= new ArrayList<>();
			Blog blog;
			try {
				ResultSet rs=createPreparedStatement("select blog_id from blogcategories where category_id ="+cat_id).executeQuery();
			while(rs.next())
			{
				int id =rs.getInt("blog_id");
				blog=getBlog(id);
				blogs.add(blog);
			}
							
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return blogs;
	 }
	 
	

}
