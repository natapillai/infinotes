package com.csye6220.infinotes.daos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.csye6220.infinotes.pojos.Role;

@Repository
public interface RoleDAOInterface {

	public void addRole(Role role);
	
	public Role findRoleByID(int id);
	
	Iterable getRoles(int id);
	
	public void updateRole(Role role);
	
	public void deleteRole(Role role);
	
}
