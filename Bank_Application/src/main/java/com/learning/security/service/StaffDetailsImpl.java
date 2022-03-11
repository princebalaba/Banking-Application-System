package com.learning.security.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learning.entity.AdminDTO;
import com.learning.entity.StaffDTO;
import com.learning.entity.UserDTO;
import com.learning.enums.EStatus;

import lombok.Data;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 2. 23.-오전 11:06:31
 */
@Data
public class StaffDetailsImpl implements UserDetails {
	private long id;
	private String username;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;
	
	private EStatus status ; 
	//Roles
	
	private StaffDetailsImpl(Long id , String username, String password, Collection<? extends GrantedAuthority> authorities, EStatus status) {
		this.id = id ; 
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.status = status;
	}
	
	public static StaffDetailsImpl build (StaffDTO staff) {
		List<GrantedAuthority> authorities = staff.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
				.collect(Collectors.toList());
	EStatus status = staff.getStatus();
		
		return new StaffDetailsImpl(staff.getId(), staff.getUsername(), staff.getPassword(), authorities, status);
		
	}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public boolean equals(Object o) {
		if( this == o) 
			return true;
			if(o == null || getClass() != o.getClass())
				return false;
			StaffDetailsImpl user = (StaffDetailsImpl) o;
			return Objects.equals(id, user.id);
		
		}

}
