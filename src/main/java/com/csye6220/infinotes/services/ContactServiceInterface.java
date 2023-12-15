package com.csye6220.infinotes.services;

import org.springframework.web.multipart.MultipartFile;

import com.csye6220.infinotes.pojos.Contact;

public interface ContactServiceInterface {

	void addNewContacts(Contact contact);
	
	Contact findContactByID(int id);
	
	Iterable<Contact> getAllContacts(int id);
	
	void updateContacts(Contact contact);
	
	void deleteContacts(Contact contact);
	
}
