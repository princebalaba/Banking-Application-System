package com.learning.exceptions;

public class SecretDetailsDoNotMatchException extends Exception {

	public SecretDetailsDoNotMatchException(String message) {
		super(message);

	}

	@Override
	public String toString() {
		return super.getMessage();
	}

}
