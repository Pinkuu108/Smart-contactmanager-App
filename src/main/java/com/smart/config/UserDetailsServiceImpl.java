package com.smart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.smart.dao.UserReposatory;
import com.smart.entity.User;

public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserReposatory userReposatory;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// fetching user from database
		User user = userReposatory.getUserByuserName(username);
		if (user == null) {
			 throw new UsernameNotFoundException("Could not found user!!");

		}
		CustomUserdetails customUserdetails = new CustomUserdetails(user);
		return customUserdetails;
	}

}
