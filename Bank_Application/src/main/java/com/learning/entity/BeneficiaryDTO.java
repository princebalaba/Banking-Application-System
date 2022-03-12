package com.learning.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.learning.enums.AccountType;
import com.learning.enums.Active;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "beneficiary_tbl")
public class BeneficiaryDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long beneficiaryId;
	private Long accountNumber;
	private String name;
	@Enumerated(EnumType.STRING)
	private Active active;
	private LocalDate addedDate;
	
	@Enumerated(EnumType.STRING)
	private AccountType accountType;


	
	
}

