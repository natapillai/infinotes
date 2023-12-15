package com.csye6220.infinotes.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csye6220.infinotes.pojos.Note;
import com.csye6220.infinotes.pojos.User;
import com.csye6220.infinotes.services.NoteService;
import com.csye6220.infinotes.services.RoleService;
import com.csye6220.infinotes.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ManageUserController {

	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	NoteService noteService;
	
	@GetMapping("/admin/allusers")
	public String handleAllUsers(HttpSession session, Model model) {
		
		Integer userId = getUserIdFromSession(session);
		
		if (userId != null) {
            User user = userService.findUserByID(userId); // Method to find user by ID
            model.addAttribute("user", user);
        }
		
		List<User> allUsers = new ArrayList<>();
		
		userService.getAllUsers().forEach(allUsers::add);	
		
		
		List<User> userRole = allUsers.stream()
									  .filter(u -> "User".equals(u.getUserRoles().get(0).getRoleName()))
									  .collect(Collectors.toList());
		
		model.addAttribute("users", userRole);
		
		return "allusers-view";
	}
	
	@PostMapping("/admin/allusers/deleteUser")
	public String deleteUser(@RequestParam("userId") Integer userId) {
		
		User user = userService.findUserByID(userId);
		
		userService.deleteUser(user);
		
		return "redirect:/admin/allusers";
	}
	
    private Integer getUserIdFromSession(HttpSession session) {
        // Implementation to retrieve userId from session
        return (Integer) session.getAttribute("userId");
    }
	
}
