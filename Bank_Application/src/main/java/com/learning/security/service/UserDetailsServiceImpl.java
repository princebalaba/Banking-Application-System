package com.learning.security.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.learning.entity.UserDTO;
import com.learning.exceptions.NoDataFoundException;
import com.learning.service.impl.StaffServiceImpl;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 2. 23.-오전 10:51:41
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	StaffServiceImpl service;
	
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDTO user = service.getUserByUserName(username).orElseThrow(()-> new NoDataFoundException("username not found " + username));
		
		
		return UserDetailsImpl.build(user);
	}

}
