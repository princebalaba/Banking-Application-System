package com.learning.payload.requset;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SignupRequest {
	// sign up request info in the payload
	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) private long customerId;
	 */
	@NotBlank
	private String userName;
	@NotBlank
	private String fullName;
	@NotBlank
	private String password;

	

}
