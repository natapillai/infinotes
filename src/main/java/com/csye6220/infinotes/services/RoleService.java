package com.csye6220.infinotes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csye6220.infinotes.daos.RoleDAOInterface;
import com.csye6220.infinotes.pojos.Role;

@Service
public class RoleService implements RoleServiceInterface{

	@Autowired
	private RoleDAOInterface roleDAO;
	
	@Override
	public void addNewRoles(Role role) {
		roleDAO.addRole(role);
	}

	@Override
	public Iterable<Role> getAllRoles(int id) {
		return roleDAO.getRoles(id);
	}

	@Override
	public Role findRoleByID(int id) {
		return roleDAO.findRoleByID(id);
	}

	@Override
	public void updateRoles(Role role) {
		roleDAO.updateRole(role);
		
	}

	@Override
	public void deleteRoles(Role role) {
		roleDAO.deleteRole(role);
		
	}

}
