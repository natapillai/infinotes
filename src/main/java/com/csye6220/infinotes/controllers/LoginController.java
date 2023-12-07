package com.csye6220.infinotes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.csye6220.infinotes.services.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

//	@Autowired
//	UserService userService;
	
//	@GetMapping("/")
////	@RequestMapping(value="/", method=RequestMethod.GET)
//	public String indexPageHandler() {
//		return "index-view";
//	}
	
	@GetMapping("/login")
	public String loginPageHandler() {
		return "login-view";
	}
	
//	@GetMapping("/login-auth")
//	public String loginAuthPageHandler(@RequestParam String username, @RequestParam String password, HttpServletRequest req) {
//		
//		
//		
//		return "login-success";
//	}
	

	
}
