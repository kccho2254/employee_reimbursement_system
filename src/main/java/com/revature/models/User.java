package com.revature.models;

public class User 
{
    // var's for id, username, password, firstName, lastName, email, roleId
	private int id;
	private int roleId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	
	public User(int id, int roleId, String username, String password, String firstName, String lastName,
			String email) {
		super();
		this.id = id;
		this.roleId = roleId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	public User() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "user id=" + id + ", roleId=" + roleId + ", username=" + username + ", password="
				+ password + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email;
	}
}
