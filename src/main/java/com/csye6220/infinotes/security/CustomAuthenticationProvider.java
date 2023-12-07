package com.csye6220.infinotes.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.csye6220.infinotes.pojos.Role;
import com.csye6220.infinotes.pojos.User;
import com.csye6220.infinotes.services.RoleService;
import com.csye6220.infinotes.services.UserService;


@Component
public class CustomAuthenticationProvider  implements AuthenticationProvider {

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;
	
	UserDetails isValidUser(String username, String password) throws IOException {
		System.out.println("point 0");
		
		User user = userService.findUserByEmail(username);

		System.out.println("point 1");
		
		List<Role> roles = new ArrayList<>();

		System.out.println("point 2");
		
		roleService.getAllRoles(user.getId()).forEach(roles::add);
		
		System.out.println("point 3");
		
		Role role = roles.get(0);
		
		System.out.println("point 4");
		
		String userPassWord = bCryptPasswordEncoder().encode(password);
		
		System.out.println("point 5");
		
		System.out.println(password);
		System.out.println(userPassWord);
		
		System.out.println("point 6");
		
		System.out.println(user.getPassword());
		
		System.out.println("point 7");
		
		if (password.equals(user.getPassword())) {
			
			UserDetails userDetails;
			
			if (role.getRoleName().equals("Admin")) {
				userDetails = org.springframework.security.core.userdetails.User.withUsername(username)
					.password("NOT_DISCLOSED").roles(String.valueOf(role.getRoleName())).build();
			} else {
				userDetails = org.springframework.security.core.userdetails.User.withUsername(username)
					.password("NOT_DISCLOSED").roles(String.valueOf(role.getRoleName())).build();
			}

			return userDetails;

		} else {
			throw new BadCredentialsException("Please enter the correct credentials !!");
		}
	}
	
	public static BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("point auth 1");
		
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		System.out.println("point auth 2");
		
		UserDetails userDetails;

		try {
			System.out.println("point auth 3");
			userDetails = isValidUser(username, password);
			System.out.println("point auth 4");
			return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());

		} catch (Exception e) {

			throw new UsernameNotFoundException("Incorrect user credentials !!");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
