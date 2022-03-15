package com.learning.security.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learning.entity.AdminDTO;
import com.learning.entity.UserDTO;

import lombok.Data;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 2. 23.-오전 11:06:31
 */

@Data
public class UserDetailsImpl implements UserDetails {
	private long id;
	private String userName;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;
	//Roles
	
	private UserDetailsImpl(Long id , String username, String password, Collection<? extends GrantedAuthority> authorities) {
		this.id = id ; 
		this.userName = username;
		this.password = password;
		this.authorities = authorities;
	}
	
	public static UserDetailsImpl build (UserDTO user) {
		List<GrantedAuthority> authorities = user.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
				.collect(Collectors.toList());
		
		
		return new UserDetailsImpl(user.getId(), user.getUsername(), user.getPassword(), authorities);
		
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
		return userName;
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
			UserDetailsImpl user = (UserDetailsImpl) o;
			return Objects.equals(id, user.id);
		
		}

}
