package com.rk.dailydish.services;

import com.rk.dailydish.dto.UserDto;

public interface UserService {

	
	
	
	UserDto  registerUser(UserDto userdto);
	
	UserDto loginUser(UserDto userdto);
	
	UserDto updateUser(UserDto userdto);
	
//	UserDto userDto = loginUser(UserDto user);
	
}
