package com.learning.payload.requset;

import com.learning.enums.AccountType;

import lombok.Data;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 9.-오후 2:49:30
 */
@Data
public class AccountRequest {
	private AccountType accountType;
	private Double accountBalance;
	private String approved;
	}
