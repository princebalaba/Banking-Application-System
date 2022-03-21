package com.learning.payload.response;

import com.learning.enums.Active;

import lombok.Data;

@Data
public class CustomerGetBeneficiaries {
	Long  beneficiaryAccountNo;
	 String   beneficiaryName;
	  Active active;
	  Long beneficiaryId;
}
