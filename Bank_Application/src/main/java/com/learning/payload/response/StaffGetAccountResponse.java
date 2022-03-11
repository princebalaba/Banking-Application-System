package com.learning.payload.response;

import java.util.Set;

import com.learning.entity.Transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffGetAccountResponse {
	
	private Long accountNo;
	private String customerName;
	private Double balance;
	private Set<Transaction> transactions;


}
