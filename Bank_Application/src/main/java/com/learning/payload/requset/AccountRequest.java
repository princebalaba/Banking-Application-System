package com.learning.payload.requset;

import lombok.Data;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 9.-오후 2:49:30
 */
@Data
public class AccountRequest {
	private String accountType;
	private Double accountBalance;
	private String approved;
	}
