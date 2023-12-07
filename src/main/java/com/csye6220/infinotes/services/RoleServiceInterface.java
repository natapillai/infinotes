package com.csye6220.infinotes.services;

import java.util.List;

import com.csye6220.infinotes.pojos.Role;

public interface RoleServiceInterface {

	void addNewRoles(Role role);
	
	Iterable<Role> getAllRoles(int id);
	
	Role findRoleByID(int id);
	
	void updateRoles(Role role);
	
	void deleteRoles(Role role);
	
	
}
