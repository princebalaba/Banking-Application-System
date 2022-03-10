package com.learning.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.learning.enums.AccountType;
import com.learning.enums.ERole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 10.-오전 12:18:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class AccountTypeDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "accountType_id")
	private long roleId;
	@NotNull
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
}
