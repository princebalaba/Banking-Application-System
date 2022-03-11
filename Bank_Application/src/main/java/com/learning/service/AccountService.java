package com.learning.service;

import com.learning.entity.AccountDTO;

public interface AccountService {
	
	AccountDTO getAccount(Long id);
	public AccountDTO updateAccount(long id, AccountDTO account);
}
