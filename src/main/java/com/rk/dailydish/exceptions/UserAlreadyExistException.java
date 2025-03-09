package com.rk.dailydish.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAlreadyExistException extends RuntimeException{

	public UserAlreadyExistException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserAlreadyExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}



}
