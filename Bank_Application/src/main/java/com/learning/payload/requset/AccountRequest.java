package com.learning.payload.requset;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.learning.enums.AccountType;

import lombok.Data;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 9.-오후 2:49:30
 */
@Data
public class AccountRequest {
	@NotNull
	private AccountType accountType;
	@NotBlank
	private Double accountBalance;
	@NotNull
	private String approved;
	}
