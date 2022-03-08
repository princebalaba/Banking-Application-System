package com.learning.response;

import com.learning.enums.AccountStatus;
import com.learning.enums.AccountType;

import lombok.Data;
@Data
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long customerId;
	private Long accountNumber;
	private AccountType accountType;
	private AccountStatus accountStatus;
	
	public JwtResponse(String accessToken, Long customerId, Long accountNumber,  
			AccountType accountType, AccountStatus accountStatus) {
		this.token = accessToken;
		this.accountNumber = accountNumber;
		this.customerId = customerId;
		this.accountType = accountType;
		this.accountStatus = accountStatus;
	}

}
