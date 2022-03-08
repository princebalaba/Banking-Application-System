package com.learning.exceptions;

import java.util.Arrays;

public class NoDataFoundException extends RuntimeException {

	public NoDataFoundException(String msg) {
		super(msg);
		
	}

	@Override
	public String toString() {
		return super.getMessage();
	}
	

	
	

}
