package com.learning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.UserDTO;
import com.learning.repo.UserRepository;
import com.learning.service.UserService;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 8.-오후 4:29:09
 */
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepo ;
	//addUser
	@Override
	public UserDTO addUser(UserDTO user) {
		// TODO Auto-generated method stub
		return userRepo.save(user);
	}
	//getUserById
	@Override
	public Optional<UserDTO> getUserById(long id) {
		return userRepo.findById(id) ;
	}


	//updateUser
	@Override
	public UserDTO updateUser(UserDTO user, long id) {
		
		return null;
	}


}
