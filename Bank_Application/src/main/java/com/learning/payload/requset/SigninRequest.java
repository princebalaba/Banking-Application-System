package com.learning.payload.requset;

import javax.validation.constraints.NotBlank;

import lombok.Data;
@Data
public class SigninRequest {
	// sign in request info in payload
	@NotBlank
	private String username;
	@NotBlank
	private String password;

}
