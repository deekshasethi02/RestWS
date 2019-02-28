package com.yash.blog_v3.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yash.blog_v3.domain.Blog;

import com.yash.blog_v3.service.BlogService;
import com.yash.blog_v3.serviceimpl.BlogServiceImpl;


@Path("blogs")
public class BlogResource {

	private BlogService blogService = new BlogServiceImpl();

	// CRUD - Get list of users
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Blog> getAllBlogs() {
		List<Blog> blogList = blogService.listBlogs();
		return blogList;
	}
	
	// CRUD - Get specific user based on id
		@GET
		@Path("{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Blog getBlogByRecordNumber(@PathParam("id") int id) {
			Blog blog = blogService.getBlog(id);
			return blog;
		}
		
		// CURD - Create blog
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)

		public Blog createBlog(Blog blog) {
			Blog blogResponse = blogService.create(blog);
			return blogResponse;
		}
		
		// CRUD - DELETE Operation
		@DELETE
		@Path("/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Blog deleteBlog(@PathParam("id") int id) {
			Blog blogResponse = blogService.destroy(id);
			return blogResponse;
		}
		
		// CRUD - Update Operation
		@PUT
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Blog updateBlog(Blog blog) {
			Blog blogResponse = blogService.edit(blog);
			return blogResponse;
		}

}
