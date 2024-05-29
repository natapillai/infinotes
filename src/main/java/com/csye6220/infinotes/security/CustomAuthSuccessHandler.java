package com.csye6220.infinotes.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.csye6220.infinotes.pojos.User;
import com.csye6220.infinotes.services.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler{

	@Autowired
	UserService userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		boolean isAdmin = false;

		for (GrantedAuthority role : authentication.getAuthorities()) {
			System.out.println(role.getAuthority());

			if (role.getAuthority().equals("ROLE_Admin")) {
				isAdmin = true;
				break;
			}
		}
		
		String email = authentication.getName();
		User user = userService.findUserByEmail(email);
		int userId = user.getId();
		
		// Set user ID in HttpSession
        HttpSession session = request.getSession();
        session.setAttribute("userId", userId);

		
		if(isAdmin) {
			response.sendRedirect("/admin/home");
		} else {
			response.sendRedirect("/user/home");
		}
		
	}

}
