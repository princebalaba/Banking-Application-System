package com.learning.payload.response;

import java.time.LocalDateTime;

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
	
	private LocalDateTime dateCreated;
	
	private String Password;

}
