package com.learning.exceptions;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 9.-오후 11:05:42
 */
public class RoleNotFoundException extends RuntimeException  {

	public RoleNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return super.getMessage();
	}
}
