package com.learning.payload.requset;

import com.learning.enums.AccountType;
import com.learning.enums.Active;

import lombok.Data;

@Data
public class BeneficiaryPayload {

	private Long accountNumber;
	private AccountType accountType;
	private Active active;
}
