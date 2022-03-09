package com.learning.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learning.enums.CreditDebit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Transaction {
	@Id
	private long transactionId;
	private LocalDateTime dateTime;
	private String reference;
	private double amount;
	private CreditDebit type;
	
	@ManyToOne
	@NotNull
	@JsonIgnore
	private AccountDTO account;
}

