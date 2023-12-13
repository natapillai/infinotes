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
		
		User user = userService.findUserByEmail(username);
		
	    if (user == null) {
	        throw new UsernameNotFoundException("User not found");
	    }
		
	    BCryptPasswordEncoder encoder = bCryptPasswordEncoder();
	    if (!encoder.matches(password, user.getPassword())) {
	        throw new BadCredentialsException("Invalid password");
	    }
		
		
		List<Role> roles = new ArrayList<>();
		
		roleService.getAllRoles(user.getId()).forEach(roles::add);
		
		System.out.println(roles);
		
		Role role = roles.get(0);
	
		UserDetails userDetails;
		
		if (role.getRoleName().equals("Admin")) {
			userDetails = org.springframework.security.core.userdetails.User.withUsername(username)
				.password("NOT_DISCLOSED").roles(String.valueOf(role.getRoleName())).build();
		} else {
			userDetails = org.springframework.security.core.userdetails.User.withUsername(username)
				.password("NOT_DISCLOSED").roles(String.valueOf(role.getRoleName())).build();
		}

		return userDetails;

		
	}
	
	public static BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		
		UserDetails userDetails;

		try {
			userDetails = isValidUser(username, password);

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
