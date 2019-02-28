package com.yash.blog_v3.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(int id, String firstName, String lastName, long contact, String email, String password, int status,
			int role, String created_at, String updated_at) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contact = contact;
		this.email = email;
		this.password = password;
		this.status = status;
		this.role = role;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	/**
	 * id of user this will be primary.
	 */
	private int id;
	
	/**
	 * firstName of user
	 */
	private String firstName;
	
	/**
	 * lastName of user
	 */
	private String lastName;
	
	/**
	 * contact of user
	 */
	private long contact;
	
	/**
	 * email id of user
	 */
	private String email;
	
	/**
	 * password of user
	 */
	private String password;
	
	/**
	 * status of user if status is 1 so its active user otherwise user not
	 * registered.)
	 */
	private int status;
	
	/**
	 * role of user if role is 1 so it will defines admin, for role 2 so its author,
	 * and for role 3 so its user.
	 */
	private int role;
	 
	/**
	 * creation date and time of user
	 */
	private String created_at;

	/**
	 * updated date and time of user.
	 */
	private String updated_at;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
		

	
}
