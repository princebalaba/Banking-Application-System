package com.learning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.UserDTO;
import com.learning.exceptions.IdNotFoundException;
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
		UserDTO prev = userRepo.findById(id).orElseThrow(()-> new IdNotFoundException("Id not found"));
			prev.setAccount(user.getAccount());
			prev.setAadhar(user.getAadhar());
			prev.setAarchar(user.getPanimage());
			prev.setFullname(user.getFullname());
			prev.setPan(user.getPan());
			prev.setPanimage(user.getPanimage());
			prev.setPhone(user.getPhone());
			prev.setSecretAnswer(user.getSecretAnswer());
			prev.setSecretQuestion(user.getSecretQuestion());
			
			userRepo.save(prev);
		return prev;

	}


}
