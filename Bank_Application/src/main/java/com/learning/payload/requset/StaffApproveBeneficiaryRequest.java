package com.learning.payload.requset;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StaffApproveBeneficiaryRequest {
	private Long fromCustomer;
	private Long beneficiaryAcNo;
}
