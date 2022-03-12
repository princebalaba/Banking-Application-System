package com.learning.payload.response;

import java.util.Set;

import com.learning.entity.Transaction;
import com.learning.enums.AccountType;
import com.learning.enums.EStatus;

import lombok.Data;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 10.-오후 5:42:41
 */
@Data
public class AccountTransactionResponse {
	private long accountNumber;

	private Double accountBalance;

	private AccountType accountType;
	
	private EStatus enabled; 
	
	Set<Transaction> transactions;

}
