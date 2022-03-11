package com.learning.payload.response;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.learning.enums.EStatus;
import com.learning.entity.AccountTypeDTO;
import com.learning.entity.ApprovedDTO;
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
	
	private AccountTypeDTO accountType;
	
	private Long accountNumber;
	
	private ApprovedDTO approved;

	private LocalDateTime dateOfCreation;
	
	private long customerId;
	

	
	

}
