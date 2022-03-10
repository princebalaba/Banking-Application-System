package com.learning.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.AccountTypeDTO;
import com.learning.enums.AccountType;
import com.learning.repo.AccountTypeRepo;
import com.learning.service.AccountTypeService;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 10.-오후 2:21:44
 */
@Service
public class AccountTypeServiceImpl implements AccountTypeService{
	@Autowired
	public AccountTypeRepo typeRepo;
	@Override
	public Optional<AccountTypeDTO> getAccountTypeByName(AccountType type) {
		// TODO Auto-generated method stub
		return typeRepo.findByAccountType(type);
	}

}
