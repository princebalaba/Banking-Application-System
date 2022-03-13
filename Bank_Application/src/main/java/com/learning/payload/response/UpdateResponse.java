package com.learning.payload.response;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 10.-오후 3:52:36
 */
@Data
public class UpdateResponse {
	@NotBlank
	private long customerId;
	@NotBlank
	private String fullname; 
	@NotBlank
	private String phone;
	@NotBlank
	private String pan; 
	@NotBlank
	private String aadhar;
	@NotBlank
	private String secretQuestion;
	@NotBlank
	private String secretAnswer;
	
	@NotBlank
	private LocalDateTime dateCreated;
	
	private byte[] panimage;
	private byte[] aarchar ;
}
