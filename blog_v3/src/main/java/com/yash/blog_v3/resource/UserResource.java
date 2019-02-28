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

import com.yash.blog_v3.domain.User;
import com.yash.blog_v3.service.UserService;
import com.yash.blog_v3.serviceimpl.UserServiceImpl;



@Path("users")
public class UserResource {

	private UserService userService = new UserServiceImpl();

	// CRUD - Get list of users
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers() {
		List<User> userList = userService.listUsers();
		return userList;
	}
	
	// CRUD - Get specific user based on id
		@GET
		@Path("{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public User getUserByRecordNumber(@PathParam("id") int id) {
			User user = userService.getUser(id);
			return user;
		}
		
		// CURD - Create user
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)

		public User createUser(User user) {
			User userResponse = userService.create(user);
			return userResponse;
		}
		
		// CRUD - DELETE Operation
		@DELETE
		@Path("/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public User deleteUser(@PathParam("id") int id) {
			User userResponse = userService.destroy(id);
			return userResponse;
		}
		
		// CRUD - Update Operation
		@PUT
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public User updateUser(User user) {
			User userResponse = userService.edit(user);
			return userResponse;
		}

}
