package com.learning.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.learning.enums.ERole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 10.-오후 4:30:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name =  "admin_tbl")
@Entity
public class AdminDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	private String username;
	private String password ;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="adminRoles",
	joinColumns  = @JoinColumn(name="id"),
	inverseJoinColumns =  @JoinColumn(name ="role_id"))
	private Set<Role> roles;
	

}
