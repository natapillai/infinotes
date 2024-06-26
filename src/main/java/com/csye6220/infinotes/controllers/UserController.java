package com.csye6220.infinotes.controllers;

import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.csye6220.infinotes.bucket.AmazonClient;
import com.csye6220.infinotes.pojos.User;
import com.csye6220.infinotes.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/user")
	public String adminHandle(){
				
		return "redirect:/user/home";
	}
	
	@GetMapping("/user/home")
	public String getUserHome(HttpSession session, Model model) {
	
		userService.printStats();
		
		Integer userId = getUserIdFromSession(session);
		
		if (userId != null) {
            User user = userService.findUserByID(userId); // Method to find user by ID
            model.addAttribute("user", user);
            
            boolean isAdmin = false;
            if(user.getUserRoles().get(0).getRoleName().equals("Admin")) {
            	isAdmin = true;
            }
            model.addAttribute("isAdmin", isAdmin);

        }
		
		
		return "userhome-view";
	}
	
	
	@GetMapping("/user/profile")
    public String profile(HttpSession session, Model model) {
        // Assume getUserIdFromSession is a method to get the userId from the session
        Integer userId = getUserIdFromSession(session);
        User user = userService.findUserByID(userId); // Fetch user details from the database

        model.addAttribute("user", user);
        return "profile-view";
    }

    @PostMapping("/user/updateProfile")
    public String updateProfile(@RequestParam("fullName") String fullName,
								@RequestParam("email") String email,
                                @RequestParam("password") String password,
                                @RequestParam("image") MultipartFile image,
                                HttpSession session,
                                Model model) throws IOException {
    	
//    	// Check file size
//        if (image.getSize() > 1048576) { // 1MB = 1048576 bytes
//            model.addAttribute("errorMessage", "File should be below 1MB");
//            return "profile"; // Return to the profile page
//        }
//
//        // Check file type
//        String contentType = image.getContentType();
//        if (!("image/png".equals(contentType) || "image/jpeg".equals(contentType) || "image/webp".equals(contentType))) {
//            model.addAttribute("errorMessage", "File type should be PNG, JPG, or WEBP");
//            return "profile"; // Return to the profile page
//        }
    	
        Integer userId = getUserIdFromSession(session);
        
        System.out.println(fullName);
        System.out.println(email);
        System.out.println(password);
        
        userService.uploadUserProfile(userId,fullName,email,password,image);

//        user.setImagePath(image);
        
        return "redirect:/user/home";
    }
	
    private Integer getUserIdFromSession(HttpSession session) {
        // Implementation to retrieve userId from session
        return (Integer) session.getAttribute("userId");
    }

}
