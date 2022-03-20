package com.learning.payload.response;


import java.util.List;

import com.learning.enums.EStatus;
import com.learning.enums.CreditDebit;

import lombok.Data;
@Data
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long customerId;
//	private Long accountNumber;
//	private CreditDebit accountType;
//	private EStatus accountStatus;
	// Ki added 
	private List<String> roles;
	private String userName;
//	public JwtResponse(String accessToken, Long customerId, Long accountNumber,  
//			CreditDebit accountType, EStatus accountStatus) {
//		this.token = accessToken;
//		this.accountNumber = accountNumber;
//		this.customerId = customerId;
//		this.accountType = accountType;
//		this.accountStatus = accountStatus;
//	}
	
	public JwtResponse(String accessToken, Long customerId, String userName,  List<String> roles) {
		this.token = accessToken;
		this.customerId = customerId;
		this.roles = roles ; 
		this.userName = userName;
		
	}
	public JwtResponse(String accessToken) {
		this.token = accessToken;
		
		
	}

}
