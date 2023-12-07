package com.csye6220.infinotes.services;

import java.util.List;

import com.csye6220.infinotes.pojos.User;

public interface UserServiceInterface {
//	boolean userAuthentication(String username, String password);
	
	public void addNewUser(User user);
	
	public User findUserByEmail(String email);
	
	public User findUserByID(int id);
	
	public Iterable<User> getAllUsers();
	
	public void updateUser(User user);
	
	public void deleteUser(User user);
	
	void saveRole(User user);
}
