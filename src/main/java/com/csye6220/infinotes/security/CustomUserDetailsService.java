package com.csye6220.infinotes.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.csye6220.infinotes.daos.UserDAO;
import com.csye6220.infinotes.pojos.User;

public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userDAO.findByEmail(username);
		
	    if (user == null) {
	        throw new UsernameNotFoundException("User not found with username: " + username);
	    }
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user));
	}
	
    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
//        return user.getUserRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toList());    
    	return null;
        }

}
