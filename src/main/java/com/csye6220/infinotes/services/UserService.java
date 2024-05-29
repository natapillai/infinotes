package com.csye6220.infinotes.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.csye6220.infinotes.bucket.AmazonClient;
import com.csye6220.infinotes.daos.UserDAO;
import com.csye6220.infinotes.pojos.Contact;
import com.csye6220.infinotes.pojos.Note;
import com.csye6220.infinotes.pojos.User;
import com.csye6220.infinotes.utils.FileUploadUtil;

@Service
public class UserService implements UserServiceInterface{

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private AmazonClient amazonClient;
	
	@Override
	public void addNewUser(User user) {
		userDAO.add(user);
	}

	@Override
	public User findUserByEmail(String email) {
		System.out.println("finduserbyemailUSERService   " + email);
		return userDAO.findByEmail(email);
	}

	@Override
	public User findUserByID(int id) {
		return userDAO.findByID(id);
	}

	@Override
	public Iterable<User> getAllUsers() {
		return userDAO.getUsers();
	}

	@Override
	public void updateUser(User user) {
		userDAO.update(user);
	}

	@Override
	public void deleteUser(User user) {
		userDAO.delete(user);
	}

	@Override
	public void saveRole(User user) {
		userDAO.saveRole(user);
	}

	public void uploadUserProfile(int userId, String fullName, String email, String password, MultipartFile imageFile) throws IOException {
	    User user = userDAO.findByID(userId);
	    
	    if (user != null) {
	    
	    	user.setFullName(fullName);
		    user.setEmail(email);
		    
	        if (password != null && !password.isEmpty()) {
	            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	            String hashedPassword = passwordEncoder.encode(password);
	            user.setPassword(hashedPassword);
	        }
		    
		    if (imageFile != null && !imageFile.isEmpty()) {
		    	
		    	
		    	String fileName = new Date().getTime() + "-" + imageFile.getOriginalFilename().replace(" ", "_");
		    	
		    	this.amazonClient.uploadFile(imageFile, fileName);
		    	
//		        String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
//		        Path uploadDirectory = Paths.get("C://InfinotesImages//");
//	
//		        // Ensure directory exists or create it
//		        if (!Files.exists(uploadDirectory)) {
//		            Files.createDirectories(uploadDirectory);
//		        }
//	
//		        // Save the file
//		        Path filePath = uploadDirectory.resolve(fileName);
//		        Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
	
		        // Set the user's imagePath
//		        user.setImagePath("/image/" + fileName);
		        user.setImagePath("https://s3.us-east-1.amazonaws.com/infinotesimagebucket/" + fileName);
	
		        
		        
		    }
		    // Update the user in the database
		    userDAO.update(user);
	    }
	}

	@Override
	public void saveNote(User user, Note note) {
		userDAO.saveNote(user, note);	
	}

	public void printStats() {
	    userDAO.printStats();
	    // Other stats can also be accessed
	}

	@Override
	public void saveContact(User user, Contact contact, MultipartFile image) throws IOException {
//    	String fileName = new Date().getTime() + "-" + image.getOriginalFilename().replace(" ", "_");
//    	
//    	this.amazonClient.uploadFile(image, fileName);
//    	
//    	contact.setContactImagePath("https://s3.us-east-1.amazonaws.com/infinotesimagebucket/" + fileName);
    	
		userDAO.saveContact(user, contact, image);
	}
	
}
