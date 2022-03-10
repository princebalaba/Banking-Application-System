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
import com.learning.enums.CreditDebit;
import com.learning.enums.EStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 10.-오후 5:44:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EnabledDisabledDTO_tbl")
public class EnabledDisabledDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enabled_id")
	private long roleId;
	@NotNull
	@Enumerated(EnumType.STRING)
	private EStatus enableOrDisabled;
}	
