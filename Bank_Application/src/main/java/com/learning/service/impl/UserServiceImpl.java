package com.learning.service.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.AccountDTO;
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
		
		return userRepo.save(user);
	}
	//getUserById
	@Override
	public Optional<UserDTO> getUserById(long id) {
	System.out.println( userRepo.findById(id).get().toString());
		return userRepo.findById(id) ;
	}


	//updateUser
	@Override
	public UserDTO updateUser(UserDTO user, long id) {
		UserDTO prev = userRepo.findById(id).orElseThrow(()-> new IdNotFoundException("Id not found"));
		
		Set<AccountDTO> accounts = prev.getAccount();
		prev.setAccount(user.getAccount());
			prev.setAadhar(user.getAadhar());
			prev.setAarchar(user.getPanimage());
			prev.setFullname(user.getFullname());
			prev.setPan(user.getPan());
			prev.setPanimage(user.getPanimage());
			prev.setPhone(user.getPhone());
			prev.setSecretAnswer(user.getSecretAnswer());
			prev.setSecretQuestion(user.getSecretQuestion());
			prev.setAccount(accounts);
			
			userRepo.save(prev);
		return prev;

	}

	@Override
	public UserDTO getUser(Long id) {
		// TODO Auto-generated method stub
		return userRepo.findById(id).orElseThrow();
	}

	@Override
	public UserDTO updateUser(UserDTO user) {
		// TODO Auto-generated method stub
		
		if(userRepo.existsById(user.getId())) {
		return	userRepo.save(user);
		}
		else {
            throw new RuntimeException("Sorry user With " + user.getId() + " not found");
        }


}}
