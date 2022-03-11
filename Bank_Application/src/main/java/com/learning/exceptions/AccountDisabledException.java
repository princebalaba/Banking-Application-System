package com.learning.exceptions;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 11.-오후 5:39:36
 */
public class AccountDisabledException  extends RuntimeException{
	public AccountDisabledException(String msg) {
		super(msg);
		
	}
	@Override
	public String toString() {
		return super.getMessage();
	}
}
