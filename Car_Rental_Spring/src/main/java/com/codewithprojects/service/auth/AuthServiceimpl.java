package com.codewithprojects.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithprojects.dto.SignupRequest;
import com.codewithprojects.dto.UserDto;
import com.codewithprojects.entity.User;
import com.codewithprojects.enums.UserRole;
import com.codewithprojects.reposatory.UserReposatory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceimpl implements AuthService {

	@Autowired
	private UserReposatory userReposatory;

	@Override
	public UserDto createCustomer(SignupRequest signupRequest) {
		User user=new User();
		user.setName(signupRequest.getName());
		user.setEmail(signupRequest.getEmail());
		user.setPassword(signupRequest.getPassword());
		user.setUserRole(UserRole.CUSTOMER);
		User createdUser=userReposatory.save(user);
		UserDto userDto=new UserDto();
		userDto.setId(createdUser.getId());
		return userDto;
	}
}
