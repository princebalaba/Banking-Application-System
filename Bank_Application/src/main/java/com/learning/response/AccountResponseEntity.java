package com.learning.response;


import java.time.LocalDate;
import java.util.List;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	@JsonFormat(pattern = "MM-dd-yyyy")
	private LocalDate dateOfCreation;
	@NotBlank
	private long customerId;
	
	
	

}
