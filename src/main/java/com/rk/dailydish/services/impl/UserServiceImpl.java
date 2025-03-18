package com.rk.dailydish.services.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rk.dailydish.config.AppConstant;
import com.rk.dailydish.dto.UserDto;
import com.rk.dailydish.entity.Role;
import com.rk.dailydish.entity.User;
import com.rk.dailydish.exceptions.ResourceNotFoundException;
import com.rk.dailydish.exceptions.UserAlreadyExistException;
import com.rk.dailydish.exceptions.UserNotFoundException;
import com.rk.dailydish.repository.RoleRepo;
import com.rk.dailydish.repository.UserRepository;
import com.rk.dailydish.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Override
	public UserDto registerUser(UserDto userDto) {
		// TODO Auto-generated method stub
		
		User user = this.dtoToUser(userDto);
		
		 Optional<User> existingPhoneUser = userrepo.findByPhone(user.getPhone());
		    if (existingPhoneUser.isPresent()) {
		        throw new UserAlreadyExistException("User with this phone number already exists!");
		    }
		
		System.out.println("dfdf"+user.getName()+user.getPhone()+user.getAddress());
		if (userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
		    throw new IllegalArgumentException("Password cannot be null or empty");
		}
		

		
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
//		user.setPassword(this.encoder.encode(user.getPassword()));
		
		Optional<Role> byId = this.roleRepo.findById(AppConstant.NORMAL_USER);
		Role role = byId.get();
		user.getRole().add(role);
		user.setCreatedAt(LocalDateTime.now());
		User newUser = this.userrepo.save(user);
		UserDto userDto1 =  this.userToDto(newUser);
	
		return userDto1;
	}
	
	
	
	@Override
	public UserDto loginUser(UserDto userDto) {
		// TODO Auto-generated method stub
		
		User user = this.dtoToUser(userDto);
		user.setPassword(this.encoder.encode(user.getPassword()));
		
		Optional<Role> byId = this.roleRepo.findById(502);
		Role role = byId.get();
		user.getRole().add(role);
		User newUser = this.userrepo.save(user);
		UserDto newUserDto = this.userToDto(newUser);
		
		return newUserDto;
		
//		Long phone = user.getPhone();
//		String password = user.getPassword();
//		
//		if(phone==null || password==null) {
//			throw new ResourceNotFoundException("please enter valid phone number and password");
//		}
//		
//		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(phone, password);
//		Authentication authenticate = authManager.authenticate(token);
//		boolean status = authenticate.isAuthenticated();
////		UserDto newUserDto = this.userToDto(user);
//		
//		if(status) {
//			Optional<User> newUserOptional = 	this.userrepo.findByPhone(phone);
//			User newUser = newUserOptional.get();
//			UserDto newUserDto = this.userToDto(newUser);
//			return newUserDto;
//		}else {
//			throw new UserNotFoundException("User Not Found");
//		}

	}



	@Override
	public UserDto updateUser(UserDto userdto) {
		// TODO Auto-generated method stub
		User user = this.dtoToUser(userdto);
		String name = user.getName();
		String phone = user.getPhone();
		String address = user.getAddress();
		
	
		return userdto;
	}



	private User dtoToUser(UserDto userDto) {
//		User user = new User();
		User user = modelmapper.map(userDto, User.class);
//		user.setName(userDto.getName());
//		user.setId(userDto.getId());
//		user.setPhone(userDto.getPhone());
//		user.setAddress(userDto.getAddress());
//		user.setTotalAmount(userDto.getTotalAmount());
//		user.setTotalOrder(userDto.getTotalOrder());

		return user;
	}
	
	private UserDto userToDto(User user) {
//		UserDto userDto = new UserDto();
		UserDto userDto = modelmapper.map(user, UserDto.class);
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setPhone(user.getPhone());
//		userDto.setAddress(user.getAddress());
//		userDto.setTotalAmount(user.getTotalAmount());
//		userDto.setTotalOrder(user.getTotalOrder());
//		userDto.setCreatedAt(user.getCreatedAt());

		return userDto;
	}

}
