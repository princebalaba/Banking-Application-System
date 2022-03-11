package com.learning.payload.requset;

import com.learning.enums.AccountType;
import com.learning.enums.Approved;

import lombok.Data;

@Data
public class BeneficiaryPayload {

	private Long accountNumber;
	private AccountType accountType;
	private Approved approved;
}
