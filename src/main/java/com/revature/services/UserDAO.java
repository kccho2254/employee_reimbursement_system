package com.revature.services;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {
    
	public User login(String email, String password);
	boolean addEmployee(User user);
	public User getEmpByUsername(String username);
	
	public User getEmpByEmail(String email);
	public List<User> getAllUsers();

}
