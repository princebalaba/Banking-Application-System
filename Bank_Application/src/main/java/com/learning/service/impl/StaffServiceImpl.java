package com.learning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.UserDTO;
import com.learning.repo.StaffRepository;
import com.learning.service.StaffService;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 8.-오후 5:27:01
 */
@Service
public class StaffServiceImpl implements StaffService{
	@Autowired
	StaffRepository repo ;
	//get Bu user Id
	@Override
	public Optional<UserDTO> getUserById(long id) {
		return repo.findById(id);
	}
	
	//get all users
	@Override
	public List<UserDTO> getAllUsers() {
		return repo.findAll();
		
	}
	//getByUserName
	@Override
	public Optional<UserDTO> getUserByUserName(String name) {
		return repo.findByUsername(name);
	}
	//does user exist
	@Override
	public boolean existsById(long id) {
		return repo.existsById(id);
		 
	}

}
