package com.learning.response;

import javax.validation.constraints.NotBlank;

import com.learning.enums.EStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountApproaval {
	// accNumber
	@NotBlank
	private long accountNumber;
	//approveStatus
	@NotBlank
	private EStatus accountStatus;
	
	

}
