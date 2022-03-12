package com.learning.payload.response;





import java.time.LocalDateTime;

import com.learning.enums.AccountType;
import com.learning.enums.Approved;
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
