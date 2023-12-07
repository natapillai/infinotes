package com.csye6220.infinotes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csye6220.infinotes.daos.UserDAO;
import com.csye6220.infinotes.pojos.User;

@Service
public class UserService implements UserServiceInterface{

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public void addNewUser(User user) {
		userDAO.add(user);
	}

	@Override
	public User findUserByEmail(String email) {
		System.out.println("finduserbyemailUSERService   " + email);
		return userDAO.findByEmail(email);
	}

	@Override
	public User findUserByID(int id) {
		return userDAO.findByID(id);
	}

	@Override
	public Iterable<User> getAllUsers() {
		return userDAO.getUsers();
	}

	@Override
	public void updateUser(User user) {
		userDAO.update(user);
	}

	@Override
	public void deleteUser(User user) {
		userDAO.delete(user);
	}

	@Override
	public void saveRole(User user) {
		userDAO.saveRole(user);
	}



}
