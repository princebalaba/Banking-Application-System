package com.learning.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 8.-오후 4:20:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class UserDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	private String username;
	@NotBlank
	private String fullname;
//	@NotBlank
	private String email;
	@NotBlank
	private String password;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "account_tbl", joinColumns = @JoinColumn(name = "account_customerId"))
	private Set<AccountDTO> account;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "role_tbl", joinColumns = @JoinColumn(name = "roleId"))
	private Set<Role> roles;
	

}
