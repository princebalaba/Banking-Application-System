package com.learning.entity;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.learning.enums.AccountType;
import com.learning.enums.ERole;

public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customerId;
	@NotBlank
	private long accountNumber;
	@NotBlank
	private Double accountBalance;
	@NotBlank
	private String approveStatus;
	@JsonFormat(pattern = "MM-dd-yyyy")
	private LocalDate dateOfCreation;
	@NotEmpty
	private ERole roles; // role of type enum
	@NotEmpty
	private AccountType accountType; // accountType of type enum

}
