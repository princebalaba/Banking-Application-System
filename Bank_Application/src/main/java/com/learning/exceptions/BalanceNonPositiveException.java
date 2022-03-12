package com.learning.exceptions;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 12.-오후 3:40:50
 */
public class BalanceNonPositiveException extends RuntimeException{
	public BalanceNonPositiveException(String msg) {
		super(msg);
		
	}
	@Override
	public String toString() {
		return super.getMessage();
	}
}