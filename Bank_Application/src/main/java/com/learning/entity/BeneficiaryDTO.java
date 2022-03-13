package com.learning.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	private LocalDateTime addedDate;
	
	@Enumerated(EnumType.STRING)
	private AccountType accountType;

	@JoinColumn(name ="user_Id")
	private Long userId;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BeneficiaryDTO other = (BeneficiaryDTO) obj;
		return Objects.equals(accountNumber, other.accountNumber) && Objects.equals(userId, other.userId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNumber, userId);
	}
	
	

	
	
}

