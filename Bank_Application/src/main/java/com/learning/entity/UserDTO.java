package com.learning.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import com.learning.enums.EStatus;

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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class UserDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	private String username;
	@NotBlank
	private String fullname;
	@NotBlank
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "account_tbl", joinColumns = @JoinColumn(name = "account_customerId"))
	private Set <AccountDTO> account;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="user_roles",
	joinColumns  = @JoinColumn(name="id"),
	inverseJoinColumns =  @JoinColumn(name ="role_id"))
	private Set<Role> roles;  
	
	private String phone;
	
	private String pan; 
	
	private String aadhar;
	
	private String secretQuestion;

	private String secretAnswer;
	
	
	private byte[] panimage;
	
	private byte[] aarchar;
	
	@Enumerated(EnumType.STRING)
	private EStatus status;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "add_beneficiary_tbl" ,  joinColumns = @JoinColumn(name ="user_id"))
	private Set<BeneficiaryDTO> beneficiaries;
	
	private LocalDateTime dateCreated;
	

}
