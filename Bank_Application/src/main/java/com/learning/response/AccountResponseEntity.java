package com.learning.response;


import java.util.List;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.learning.enums.EStatus;
import com.learning.enums.CreditDebit;

import lombok.Data;

@Data
public class AccountResponseEntity {
	@NotBlank
	private Double accountBalance;
	@NotBlank
	private CreditDebit accountType;
	@NotBlank
	private Long accountNumber;
	@NotNull
	private EStatus accountStatus;
	@NotNull
	private List <String> transaction;
	
	

}
