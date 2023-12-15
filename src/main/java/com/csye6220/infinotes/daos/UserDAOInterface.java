package com.csye6220.infinotes.daos;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.csye6220.infinotes.pojos.Contact;
import com.csye6220.infinotes.pojos.Note;
import com.csye6220.infinotes.pojos.User;

@Repository
public interface UserDAOInterface {
	
	public void saveUser(User user);
	
	public void saveRole(User user);
	
	public void saveNote(User user, Note note);
	
	public void saveContact(User user, Contact contact, MultipartFile image) throws IOException;
	
	public User findByID(int id);
	
	public User findByEmail(String email);
	
	public void update(User user);
	
	public Iterable getUsers();
	
	public void add(User user);
	
	public void delete(User user);

}
