package com.learning.payload.requset;

import java.io.File;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 10.-오후 3:21:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRequest {
	@NotNull
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
	
	private MultipartFile  panimage;
	private MultipartFile  aarchar ;
}
