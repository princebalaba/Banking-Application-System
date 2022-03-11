package com.learning.payload.requset;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 11.-오후 4:51:22
 */
@Data
public class StaffSignupRequeset {
	@NotBlank
	private String staffUserName;
	@NotBlank
	private String staffFullName;
	@NotBlank
	private String staffPassword;
}
