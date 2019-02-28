package com.yash.restapp.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yash.restapp.domain.Blog;
import com.yash.restapp.service.BlogService;
import com.yash.restapp.serviceimpl.BlogServiceImpl;
@Path("blogs")
public class BlogResource {
	BlogService blogService =  new BlogServiceImpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Blog> listOfBlogs(){
		return blogService.listBlogs();
	}
	
	@GET
	@Path("/{recordnumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public Blog get(@PathParam("recordnumber")int recordnumber) {
		return blogService.findbyId(recordnumber-1);
	}
	
}
