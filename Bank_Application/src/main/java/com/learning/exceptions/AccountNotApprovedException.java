package com.learning.exceptions;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 20.-오후 5:12:36
 */
public class AccountNotApprovedException extends RuntimeException{
	public AccountNotApprovedException(String msg) {
		super(msg);
		
	}
	@Override
	public String toString() {
		return super.getMessage();
	}
}