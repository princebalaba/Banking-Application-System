package com.learning.payload.response;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 12.-오후 4:14:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCustomerResponse {
	private String username;
	private String fullName;
	private String phone;
	private String pan;
	private String aadhar;
}
