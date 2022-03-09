package com.learning.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.learning.enums.Approved;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BeneficiaryDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long beneficiaryId;
	private long accountNo;
	private String name;
	private Approved active;
	private LocalDate addedDate;
	
}

