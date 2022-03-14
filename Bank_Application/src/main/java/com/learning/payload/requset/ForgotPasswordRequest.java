package com.learning.payload.requset;

import lombok.Data;

@Data
public class ForgotPasswordRequest {
	
	private String username;
	private String securityAnswer;

}
