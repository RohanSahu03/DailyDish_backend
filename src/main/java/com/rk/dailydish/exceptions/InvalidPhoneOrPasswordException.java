package com.rk.dailydish.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidPhoneOrPasswordException extends RuntimeException{

	public InvalidPhoneOrPasswordException() {
		super();
	}

	public InvalidPhoneOrPasswordException(String message) {
		super(message);
	}

}
