package com.learning.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.learning.enums.Types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class AccountDTO {
	
	@Column(name = "account_customerId")
	private long customerId;
	@NotBlank
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long accountNumber;
	@NotBlank
	private Double accountBalance;
	
	@JsonFormat(pattern = "MM-dd-yyyy")
	private LocalDate dateOfCreation;
	// enum enable/disable
	@NotEmpty
	private Types accountType; // accountType of type enum

}
