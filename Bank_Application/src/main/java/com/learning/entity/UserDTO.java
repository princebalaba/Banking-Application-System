package com.learning.entity;

import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

public class UserDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	private String userName;
	@NotBlank
	private String fullName;
	@NotBlank
	private String password;
	@Embedded
	private AccountDTO account;
	@Embedded
	private Set<Role> roles ;
	

}
