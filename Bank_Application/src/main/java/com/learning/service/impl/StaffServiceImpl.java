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
	@Override
	public Optional<UserDTO> getUserById(long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<UserDTO> getUserByUserName(String name) {
		// TODO Auto-generated method stub
		return repo.findByUsername(name);
	}

	@Override
	public boolean existsById(long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
