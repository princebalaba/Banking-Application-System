package com.learning.payload.requset;

import javax.validation.constraints.NotBlank;

public class SigninRequest {
	//sign in request info in payload
	@NotBlank
	private String userName;
	@NotBlank
	private String password;

}
