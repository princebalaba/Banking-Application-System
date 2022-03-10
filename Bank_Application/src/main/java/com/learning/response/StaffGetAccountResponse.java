package com.learning.response;

import java.util.Set;

import com.learning.entity.Transaction;

import lombok.Data;

@Data
public class StaffGetAccountResponse {
	
	private Long accountNo;
	private String customerName;
	private Double balance;
	private Set<Transaction> transactions;


}
