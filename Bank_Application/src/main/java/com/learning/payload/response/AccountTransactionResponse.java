package com.learning.payload.response;

import java.util.Set;

import com.learning.entity.AccountTypeDTO;
import com.learning.entity.EnabledDisabledDTO;
import com.learning.entity.Transaction;

import lombok.Data;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 10.-오후 5:42:41
 */
@Data
public class AccountTransactionResponse {
	private long accountNumber;

	private Double accountBalance;

	private AccountTypeDTO accountType;
	
	private EnabledDisabledDTO enabled; 
	
	Set<Transaction> transactions;

}
