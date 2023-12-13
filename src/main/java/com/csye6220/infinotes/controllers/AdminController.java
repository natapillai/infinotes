package com.csye6220.infinotes.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.csye6220.infinotes.pojos.User;
import com.csye6220.infinotes.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	@Autowired
	UserService userService;
	
	@GetMapping("/admin")
	public String adminHandle(){
				
		return "redirect:/admin/home";
	}
	
	@GetMapping("/admin/home")
	public String adminHandle(HttpSession session, Model model){
				
		Integer userId = getUserIdFromSession(session);
		
		if (userId != null) {
            User user = userService.findUserByID(userId); // Method to find user by ID
            model.addAttribute("user", user);
        }
		
		return "adminhome-view";
	}
	
	@GetMapping("/admin/profile")
    public String profile(HttpSession session, Model model) {
        // Assume getUserIdFromSession is a method to get the userId from the session
        Integer userId = getUserIdFromSession(session);
        User user = userService.findUserByID(userId); // Fetch user details from the database

        model.addAttribute("user", user);
        return "adminprofile-view";
    }
	
	@PostMapping("/admin/updateProfile")
    public String updateProfile(@RequestParam("email") String email,
                                @RequestParam("password") String password,
                                @RequestParam("image") MultipartFile image,
                                HttpSession session) throws IOException {
        Integer userId = getUserIdFromSession(session);
        
        System.out.println(email);
        System.out.println(password);
        
        userService.uploadUserProfile(userId,email,password,image);

//        user.setImagePath(image);
        
        return "redirect:/admin/home";
    }
	
    private Integer getUserIdFromSession(HttpSession session) {
        // Implementation to retrieve userId from session
        return (Integer) session.getAttribute("userId");
    }
	
}
