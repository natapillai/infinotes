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
//@Component
public class User {
	
	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="fullName")
	private String fullName;
	
	@Column(unique=true)
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="imagePath")
	private String imagePath;
	
	@OneToMany(fetch = FetchType.EAGER,
			   mappedBy="user",
			   cascade= {CascadeType.ALL})
	List<Role> userRoles = new ArrayList<Role>();
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade= {CascadeType.ALL})
	List<Note> userNotes = new ArrayList<Note>();
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade= {CascadeType.ALL})
	List<Contact> userContacts = new ArrayList<Contact>();
	
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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
	

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public void addUserRole(Role role) {
		this.userRoles.add(role);
		role.setUser(this);
	}
	
	public List<Role> getUserRoles(){
		return this.userRoles;
	}
	
	public void setUserNote(Note note) {
		this.userNotes.add(note);
		note.setUser(this);
	}
	
	public List<Note> getUserNotes(){
		return this.userNotes;
	}
	
	public void setUserContact(Contact contact) {
		this.userContacts.add(contact);
		contact.setUser(this);
	}
	
	public List<Contact> getUserContacts(){
		return this.userContacts;
	}
	

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + "]";
	}
	
}
