package com.csye6220.infinotes.pojos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
@Component
public class User {
	
	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(unique=true)
	private String email;
	
	@Column(name="password")
	private String password;
	
//	@Column(name="role")
//	private String role;
	
//	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	@OneToMany(fetch = FetchType.LAZY,
			   mappedBy="user",
			   cascade= {CascadeType.PERSIST, CascadeType.MERGE,
						 CascadeType.DETACH, CascadeType.REFRESH})
	List<Role> userRoles = new ArrayList<Role>();
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	List<Note> userNotes = new ArrayList<Note>();
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public User() {
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	
	
//	public String getRole() {
//		return role;
//	}
//
//	public void setRole(String role) {
//		this.role = role;
//	}

	public void addUserRole(Role role) {
		this.userRoles.add(role);
		role.setUser(this);
	}
	
	public List<Role> getUserRoles(){
		return this.userRoles;
	}
	
	public void setUserNote(Note note) {
		this.userNotes.add(note);
	}
	
	public List<Note> getUserNotes(){
		return this.userNotes;
	}
	
}
