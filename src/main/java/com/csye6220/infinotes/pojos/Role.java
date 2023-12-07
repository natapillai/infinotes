package com.csye6220.infinotes.pojos;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.JoinColumn;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="role")
@Component
public class Role {
	
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="roleId")
	private int roleId;
	
	@Column(name="roleName")
	private String roleName;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "id")
	private User user;
	
	
	
	public Role() {}

	public Role(String roleName) {
		this.roleName = roleName;
	}
	
	public int getRoleId() {
		return roleId;
	}
	
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
