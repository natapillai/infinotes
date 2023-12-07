package com.csye6220.infinotes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.csye6220.infinotes.pojos.User;
import com.csye6220.infinotes.services.UserService;

@Controller
public class SignupController {

	@Autowired
	UserService userService;
	
	@GetMapping("/signup")
	public String showSignUp(Model model) {
		User user = new User();
		model.addAttribute("user",user);
		return "signup-view";
	}
	
	@PostMapping("/signup")
	public String submitSignUp(@ModelAttribute("user") User user) {
		
		String userEmail = user.getEmail();
		String userPassword = user.getPassword();
		
		user.setEmail(userEmail);
		user.setPassword(userPassword);
		
		userService.addNewUser(user);
		userService.saveRole(user);
		
		System.out.println("Submit SignUp!");
		
		return "redirect:/";	
	}
	
}
