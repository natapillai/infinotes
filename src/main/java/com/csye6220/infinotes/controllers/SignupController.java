package com.csye6220.infinotes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String submitSignUp(@ModelAttribute("user") User user, Model model, @RequestParam String confirmPassword) {
		
		String userEmail = user.getEmail();
		String userPassword = user.getPassword();

		System.out.println(userEmail);
		System.out.println(userPassword);
		System.out.println(confirmPassword);
		
	    // Check if user already exists
	    if (userService.findUserByEmail(userEmail) != null) {
	        model.addAttribute("errorMessage", "Email already in use");
	        return "signup-view";
	    }
	    
	    // Check if passwords match
	    if (!userPassword.equals(confirmPassword)) {
	        model.addAttribute("errorMessage", "Passwords do not match");
	        return "signup-view";
	    }
		
		user.setEmail(userEmail);
		user.setPassword(userPassword);
		
		userService.addNewUser(user);
		userService.saveRole(user);
		
		System.out.println("Submit SignUp!");
		
		return "redirect:/";	
	}
	
}
