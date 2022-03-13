package com.learning.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.learning.enums.AccountType;
import com.learning.enums.Approved;
import com.learning.enums.CreditDebit;
import com.learning.enums.EStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accountDTO_tbl")
public class AccountDTO {
	
	@Column(name = "account_customerId")
	private long customerId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long accountNumber;
	
	private Double accountBalance;
	
	
	private LocalDateTime dateOfCreation;
	// enum enable/disable
//	@Enumerated(EnumType.STRING)
//	private CreditDebit type; // accountType of type enum
	// 
	
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	
	@Enumerated(EnumType.STRING)
	private Approved approved;
	

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "tranactions" , joinColumns = @JoinColumn (name = "transAction_id" ))
	private Set<Transaction> transactions;
	
	@Enumerated(EnumType.STRING)
	private EStatus enableDisabled;


}
