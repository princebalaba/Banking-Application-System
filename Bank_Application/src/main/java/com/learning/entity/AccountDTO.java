package com.learning.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "creditDebitTypeDTO", joinColumns = @JoinColumn(name = "credit_id"))
	private CreditDebitTypeDTO type; // accountType of type enum
	// 
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "accountTypeDTO", joinColumns = @JoinColumn(name = "accountType_id"))
	private AccountTypeDTO accountType;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable( name = "approvedDTO" , joinColumns = @JoinColumn(name = "approved_id"))
	private ApprovedDTO approved;
	
	@OneToMany()
	@JoinTable(name = "tranactions" , joinColumns = @JoinColumn (name = "transAction_id" ))
	private Set<Transaction> transactions;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable( name = "enabledDTO" , joinColumns = @JoinColumn(name = "enabled_id"))
	private EnabledDisabledDTO enableDisabled;

}
