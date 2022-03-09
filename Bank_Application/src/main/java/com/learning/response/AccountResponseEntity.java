package com.learning.response;


import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.learning.enums.EStatus;
import com.learning.enums.CreditDebit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
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

	@JsonFormat(pattern = "MM-dd-yyyy")
	private LocalDate dateOfCreation;
	@NotBlank
	private long customerId;
	@NotNull
	private List <String> transaction;

	
	

}
