package com.csye6220.infinotes.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.csye6220.infinotes.daos.ContactDAOInterface;
import com.csye6220.infinotes.pojos.Contact;

@Service
public class ContactService implements ContactServiceInterface{

	@Autowired
	private ContactDAOInterface contactDAO;
	
	@Override
	public void addNewContacts(Contact contact) {
		contactDAO.saveContact(contact);
	}
	
	public void saveContactWithImage(Contact contact, MultipartFile imageFile) throws IOException {
		if (imageFile != null && !imageFile.isEmpty()) {
	        String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
	        Path uploadDirectory = Paths.get("C://InfinotesImages//");

	        // Ensure directory exists or create it
	        if (!Files.exists(uploadDirectory)) {
	            Files.createDirectories(uploadDirectory);
	        }

	        // Save the file
	        Path filePath = uploadDirectory.resolve(fileName);
	        Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

	        // Set the user's imagePath
	        contact.setContactImagePath("/image/" + fileName);
	        
	    }
		contactDAO.saveContact(contact);
	}

	@Override
	public Contact findContactByID(int id) {
		return contactDAO.findContactbyID(id);
	}

	@Override
	public Iterable<Contact> getAllContacts(int id) {
		return contactDAO.getContacts(id);
	}
	
	@Override
	public void updateContacts(Contact contact) {
		contactDAO.updateContact(contact);
	}

	public void updateContactsWithImage(Contact contact, MultipartFile imageFile) throws IOException {
		if (imageFile != null && !imageFile.isEmpty()) {
	        String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
	        Path uploadDirectory = Paths.get("C://InfinotesImages//");

	        // Ensure directory exists or create it
	        if (!Files.exists(uploadDirectory)) {
	            Files.createDirectories(uploadDirectory);
	        }

	        // Save the file
	        Path filePath = uploadDirectory.resolve(fileName);
	        Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

	        // Set the user's imagePath
	        contact.setContactImagePath("/image/" + fileName);
	        
	    }
		contactDAO.updateContact(contact);
	}

	@Override
	public void deleteContacts(Contact contact) {
		contactDAO.deleteContact(contact);
	}



}
