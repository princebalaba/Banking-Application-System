package com.learning.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learning.enums.AccountType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

	private long transactionId;
	private LocalDateTime dateTime;
	private String reference;
	private double amount;
	private AccountType type;
	
	@ManyToOne
	@NotNull
	@JsonIgnore
	private AccountDTO account;
	
}
