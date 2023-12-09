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
		System.out.println("AuthSuccess 1");
		for (GrantedAuthority role : authentication.getAuthorities()) {
			System.out.println(role.getAuthority());
			System.out.println("AuthSuccess 2");
			if (role.getAuthority().equals("ROLE_Admin")) {
				isAdmin = true;
				break;
			}
		}
		
		if(isAdmin) {
			System.out.println("AuthSuccess 3");
			response.sendRedirect("/admin/adminIndex");
		} else {
			System.out.println("AuthSuccess 4");
			response.sendRedirect("/user/home");
		}
		
	}

}
