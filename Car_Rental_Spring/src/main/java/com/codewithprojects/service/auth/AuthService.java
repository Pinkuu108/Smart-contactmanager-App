package com.codewithprojects.service.auth;

import com.codewithprojects.dto.SignupRequest;
import com.codewithprojects.dto.UserDto;

public interface AuthService {

	
	UserDto createCustomer(SignupRequest signupRequest);
}
