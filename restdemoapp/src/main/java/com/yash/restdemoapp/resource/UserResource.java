package com.yash.restdemoapp.resource;

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

import com.yash.restdemoapp.model.User;
import com.yash.restdemoapp.service.UserService;
import com.yash.restdemoapp.serviceimpl.UserServiceImpl;

@Path("users")
public class UserResource {

	private UserService userService = new UserServiceImpl();

	// CRUD - Get list of users
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers() {
		List<User> userList = userService.getAllUsers();
		return userList;
	}

	// CRUD - Get specific user based on id
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserByRecordNumber(@PathParam("id") int id) {
		User user = userService.findById(id);
		return user;
	}

	// CURD - Create user
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)

	public User createUser(User user) {
		User userResponse = userService.addUser(user);
		return userResponse;
	}

	// CRUD - DELETE Operation
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User deleteUser(@PathParam("id") int id) {
		User userResponse = userService.deleteUser(id);
		return userResponse;
	}

	// CRUD - Update Operation
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User updateUser(User user) {
		User userResponse = userService.updateUser(user);
		return userResponse;
	}

}
