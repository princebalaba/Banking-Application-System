package com.learning.payload.requset;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 13.-오후 3:45:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequestStaff {
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
	private String  staff;
}
