package com.csye6220.infinotes.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.csye6220.infinotes.pojos.Note;
import com.csye6220.infinotes.pojos.User;
import com.csye6220.infinotes.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ManageUserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/admin/allusers")
	public String handleAllUsers(HttpSession session, Model model) {
		
		Integer userId = getUserIdFromSession(session);
		
		if (userId != null) {
            User user = userService.findUserByID(userId); // Method to find user by ID
            model.addAttribute("user", user);
        }
		
		List<User> users = new ArrayList<>();
		
		userService.getAllUsers().forEach(users::add);
		
		model.addAttribute("users", users);
		
		return "allusers-view";
	}
	
    private Integer getUserIdFromSession(HttpSession session) {
        // Implementation to retrieve userId from session
        return (Integer) session.getAttribute("userId");
    }
	
}
