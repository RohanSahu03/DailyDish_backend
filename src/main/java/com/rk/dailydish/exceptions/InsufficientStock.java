package com.rk.dailydish.exceptions;

public class InsufficientStock extends RuntimeException{

	public InsufficientStock(String message) {
		super(message);
	}

	public InsufficientStock() {
		super();
		// TODO Auto-generated constructor stub
	}

}
