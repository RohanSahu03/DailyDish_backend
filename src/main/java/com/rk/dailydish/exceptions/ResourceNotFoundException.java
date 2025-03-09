package com.rk.dailydish.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

	
	String resourceName;
	String fieldName;
	long  feildValue;
	public ResourceNotFoundException(String resourceName, String fieldName, long feildValue) {
		super(String.format("%s not found with %s : %s",resourceName,fieldName,feildValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.feildValue = feildValue;
	}
	public ResourceNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	
	
}
