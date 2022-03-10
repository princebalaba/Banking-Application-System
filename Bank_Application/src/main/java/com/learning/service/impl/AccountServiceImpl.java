package com.learning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.AccountDTO;
import com.learning.exceptions.IdNotFoundException;
import com.learning.repo.AccountRepo;
import com.learning.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountRepo repo;

	@Override
	public AccountDTO getAccount(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElseThrow(
				
			() -> new IdNotFoundException("account not found")	);
	}
}
