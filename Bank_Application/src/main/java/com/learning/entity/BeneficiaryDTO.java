package com.learning.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.learning.enums.Approved;

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
	private long beneficiaryAccount;
	private long accountNo;
	private String name;
	private Approved active;
	private LocalDate addedDate;
	

	
	
}

