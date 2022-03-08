package com.learning.response;


import java.util.List;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.learning.enums.EStatus;
import com.learning.enums.Types;

import lombok.Data;

@Data
public class AccountResponseEntity {
	@NotBlank
	private Double accountBalance;
	@NotBlank
	private Types accountType;
	@NotBlank
	private Long accountNumber;
	@NotBlank
	private EStatus accountStatus;
	@NotEmpty
	private List <String> transaction;
	
	

}
