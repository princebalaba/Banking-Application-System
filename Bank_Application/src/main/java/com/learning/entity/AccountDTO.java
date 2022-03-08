package com.learning.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.learning.enums.AccountType;
import com.learning.enums.ERole;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AccountDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customerId;
	@NotBlank
	private long accountNumber;
	@NotBlank
	private Double accountBalance;
	
	@JsonFormat(pattern = "MM-dd-yyyy")
	private LocalDate dateOfCreation;
	// enum enable/disable
	@NotEmpty
	private AccountType accountType; // accountType of type enum

}
