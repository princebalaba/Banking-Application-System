package com.learning.response;


import com.learning.enums.EStatus;
import com.learning.enums.Types;

import lombok.Data;
@Data
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long customerId;
	private Long accountNumber;
	private Types accountType;
	private EStatus accountStatus;
	
	public JwtResponse(String accessToken, Long customerId, Long accountNumber,  
			Types accountType, EStatus accountStatus) {
		this.token = accessToken;
		this.accountNumber = accountNumber;
		this.customerId = customerId;
		this.accountType = accountType;
		this.accountStatus = accountStatus;
	}

}
