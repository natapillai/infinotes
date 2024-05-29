package com.csye6220.infinotes.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.csye6220.infinotes.pojos.Contact;
import com.csye6220.infinotes.pojos.Note;
import com.csye6220.infinotes.pojos.User;
import com.csye6220.infinotes.services.ContactService;
import com.csye6220.infinotes.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ContactController {

	@Autowired
	UserService userService;
	
	@Autowired
	ContactService contactService;
	
	@GetMapping("/user/contacts")
	public String handleAllNotes(HttpSession session, Model model) {
		
		Integer userId = getUserIdFromSession(session);
		
		if (userId != null) {
            User user = userService.findUserByID(userId);
            model.addAttribute("user", user);
        }
		
		List<Contact> contacts = new ArrayList<>();
		
		contactService.getAllContacts(userId).forEach(contacts::add);
		
		model.addAttribute("contacts", contacts);
		
		return "allcontacts-view";
	}
	
	@GetMapping("/user/contact/new")
	public String newNote(HttpSession session, Model model) {
		
		Integer userId = getUserIdFromSession(session);
		
		if (userId != null) {
            User user = userService.findUserByID(userId);
            model.addAttribute("user", user);
        }
		
		Contact contact = new Contact();
		model.addAttribute("contact",contact);
		
		return "createContact-view";
	}
	
	@PostMapping("/user/contact/newContact")
	public String handleNewNote(@RequestParam("contactName") String contactName,
            @RequestParam("contactEmail") String contactEmail,
            @RequestParam("contactNumber") String contactNumber,
            @RequestParam("contactAddress") String contactAddress,
            @RequestParam("contactImagePath") MultipartFile image, HttpSession session, Model model) throws IOException {
		
		Contact contact = new Contact();
		
		Integer userId = getUserIdFromSession(session);
		
        User user = userService.findUserByID(userId);
		
		contact.setContactName(contactName);
		contact.setContactEmail(contactEmail);
		contact.setContactNumber(contactNumber);
		contact.setContactAddress(contactAddress);
		
		System.out.println("contactName " + contactName);
		System.out.println("contactEmail " + contactEmail);
		System.out.println("contactNumber " + contactNumber);
		System.out.println("contactAddress " + contactAddress);
		
	    userService.saveContact(user, contact, image);
		
		return "redirect:/user/contacts";
	}
	
	@GetMapping("/user/contact/edit/{id}")
    public String editNote(@PathVariable("id") int contactId, Model model, HttpSession session) {
    	
		Integer userId = getUserIdFromSession(session);
		
		if (userId != null) {
            User user = userService.findUserByID(userId);
            model.addAttribute("user", user);
        }
    	
        Contact contact = contactService.findContactByID(contactId);
        model.addAttribute("contact", contact);
        return "editContact-view";
    }
   
    @PostMapping("/user/contact/edit")
    public String handleNoteEdit(@RequestParam("contactId") int contactId,
                                @RequestParam("contactName") String contactName,
                                @RequestParam("contactEmail") String contactEmail,
                                @RequestParam("contactNumber") String contactNumber,
                                @RequestParam("contactAddress") String contactAddress,
                                @RequestParam("contactImagePath") MultipartFile image) throws IOException {
       
	   Contact contact = contactService.findContactByID(contactId);
	   
       if (contact != null) {

           if (!contact.getContactName().equals(contactName)) {
        	   contact.setContactName(contactName);
           }

           if (!contact.getContactEmail().equals(contactEmail)) {
        	   contact.setContactEmail(contactEmail);
           }
           
           if (!contact.getContactNumber().equals(contactNumber)) {
        	   contact.setContactNumber(contactNumber);
           }
           
           if (!contact.getContactAddress().equals(contactAddress)) {
        	   contact.setContactAddress(contactAddress);
           }
        	   
           contactService.updateContactsWithImage(contact, image);
           
       }

       return "redirect:/user/contacts"; 
    }
   
    @PostMapping("/user/contact/delete/{id}")
    public String deleteNote(@PathVariable("id") int contactId) {
    	Contact contact = contactService.findContactByID(contactId);
        contactService.deleteContacts(contact);
        return "redirect:/user/contacts"; 
    }
	
	private Integer getUserIdFromSession(HttpSession session) {
	    return (Integer) session.getAttribute("userId");
	}
	
}
