package com.learning.payload.requset;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 11.-오후 12:32:07
 */
@Data
public class TransferRequest {
	@NotNull
	private long fromAccNumber;
	@NotNull
	private long toAccNumber;
	@NotNull
	private Double amount;
	@NotBlank
	private String reason;
	//set long beacuse not sure about customerDTO or id
	@NotNull
	private long customer;
}
