package com.csye6220.infinotes.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		boolean isAdmin = false;
		
		for (GrantedAuthority role : authentication.getAuthorities()) {
			if (role.getAuthority().equals("ROLE_Admin")) {
				isAdmin = true;
				break;
			}
		}
		
		if(isAdmin) {
			response.sendRedirect("/adminIndex");
		} else {
			response.sendRedirect("/login");
		}
		
	}

}
