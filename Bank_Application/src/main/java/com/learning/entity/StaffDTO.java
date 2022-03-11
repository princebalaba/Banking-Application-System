package com.learning.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.learning.enums.EStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StaffDTO extends UserDTO {
	
	
	private EStatus status = EStatus.ENABLE;
	
	@OneToMany
	private Set<Role> roles; 
	
	@NotBlank
	private String username;
	@NotBlank
	private String fullname;
	@NotBlank
	private String password;

}
