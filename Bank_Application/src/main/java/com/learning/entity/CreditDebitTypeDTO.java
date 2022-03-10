package com.learning.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.learning.enums.EStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "typeDTO_tbl")
public class CreditDebitTypeDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long roleId;
	@NotNull
	@Enumerated(EnumType.STRING)
	private EStatus estatus;
}
