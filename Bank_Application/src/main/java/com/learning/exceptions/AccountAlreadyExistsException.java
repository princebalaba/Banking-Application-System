package com.learning.exceptions;

public class AccountAlreadyExistsException extends RuntimeException {
	
	public AccountAlreadyExistsException(String e) {
		// TODO Auto-generated constructor stub
		super(e);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}

}