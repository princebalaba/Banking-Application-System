package com.learning.exceptions;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 12.-오후 6:02:49
 */
public class UnauthrorizedException extends RuntimeException{
	public UnauthrorizedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return super.getMessage();
	}
}