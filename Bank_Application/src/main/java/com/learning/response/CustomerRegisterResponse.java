package com.learning.response;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerRegisterResponse {
	
	@NotBlank
	private String userName;
	@NotBlank
	private String fullName;
	
	@NotBlank
	private Long customerId;
	
	private String Password;
	

}
