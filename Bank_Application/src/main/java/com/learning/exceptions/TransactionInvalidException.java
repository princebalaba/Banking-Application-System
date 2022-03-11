package com.learning.exceptions;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 11.-오후 2:08:50
 */
public class TransactionInvalidException extends RuntimeException{
	public TransactionInvalidException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return super.getMessage();
	}
}
