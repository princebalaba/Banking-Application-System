package com.learning.security_service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.learning.entity.User;
import com.learning.exceptions.NoDataFoundException;
import com.learning.repository.UserRepository;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 2. 23.-오전 10:51:41
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepo;
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username).orElseThrow(()-> new NoDataFoundException("username not found " + username));
		
		
		return UserDetailsImpl.build(user);
	}

}
