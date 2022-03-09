package com.learning.response;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.learning.enums.EStatus;
import com.learning.enums.AccountType;
import com.learning.enums.Approved;
import com.learning.enums.CreditDebit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountResponseEntity {
	
	private Double accountBalance;
	
	private AccountType accountType;
	
	private Long accountNumber;
	
	private Approved approved;

	private LocalDateTime dateOfCreation;
	
	private long customerId;
	

	
	

}
