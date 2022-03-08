package com.learning.response;

import javax.validation.constraints.NotBlank;

import com.learning.enums.AccountType;

import lombok.Data;

@Data
public class AccountResponseEntity {
	@NotBlank
	private Long accountNumber1;
	private AccountType accountType;
	private Long accountNumber;
	

}
