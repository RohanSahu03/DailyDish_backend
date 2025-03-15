package com.rk.dailydish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rk.dailydish.dto.UserDto;
import com.rk.dailydish.exceptions.InvalidPhoneOrPasswordException;
import com.rk.dailydish.payload.JwtAuthRequest;
import com.rk.dailydish.payload.JwtAuthResponse;
import com.rk.dailydish.security.CustomeUserDetailService;
import com.rk.dailydish.security.JwtHelper;
import com.rk.dailydish.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private CustomeUserDetailService customeUserDetailService;

    @Autowired  
    private AuthenticationManager authenticationManager;
    

	@Autowired
	private UserService userService;
	

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@Valid @RequestBody JwtAuthRequest request) {
        System.out.println("User phone: " + request.getPhone());

        // Convert phone (long) to String for authentication
        String phoneString = String.valueOf(request.getPhone());

        this.authenticate(phoneString, request.getPassword());

        UserDetails userDetails = customeUserDetailService.loadUserByUsername(phoneString);
        String token = this.jwtHelper.generateToken(userDetails);

        JwtAuthResponse resp = new JwtAuthResponse();
        resp.setToken(token);

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    private void authenticate(String phone, String password) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(phone, password);

            Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        }
        catch (DisabledException e) {
            throw new RuntimeException("User is disabled!", e);
        }
        catch (BadCredentialsException e) {
            throw new InvalidPhoneOrPasswordException("Invalid Phone or Password !!");
        }
    }
    
    
    
    @PostMapping("/register")
	public ResponseEntity<UserDto>  registerUser(@Valid @RequestBody UserDto userDto){
		UserDto createdUserDto = this.userService.registerUser(userDto);
	System.out.println("hiii"+createdUserDto.getPassword());
		return new ResponseEntity<UserDto>(createdUserDto, HttpStatus.CREATED);
//	return new ResponseEntity<>(new ApiResponse("user created",true), HttpStatus.CREATED);
		
	}
	
}
