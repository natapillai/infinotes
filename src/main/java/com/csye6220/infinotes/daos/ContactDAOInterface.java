package com.csye6220.infinotes.daos;

import org.springframework.stereotype.Repository;

import com.csye6220.infinotes.pojos.Contact;

@Repository
public interface ContactDAOInterface {

	public void saveContact(Contact contact);
	
	public Contact findContactbyID(int id);
	
	Iterable getContacts(int id);
	
	public void updateContact(Contact contact);
	
	public void deleteContact(Contact contact);
	
}
