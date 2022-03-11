package com.learning.service;

import java.util.List;

import com.learning.entity.AccountDTO;

public interface AccountService {
	
	AccountDTO getAccount(Long id);
	AccountDTO getAccountById(Long id);
	AccountDTO getAccountByAccountNumber(Long id);
	List<AccountDTO> getAllAcounts();
	List<AccountDTO> getAllApprovedAcounts();
	List<AccountDTO> getAllUnapprovedAcounts();
	Boolean removeAccountById(Long id);
	AccountDTO updateAccount(Long id, AccountDTO newAccount);
	AccountDTO createAccount(AccountDTO account);
	Boolean accountExists(Long id);
	

	public AccountDTO updateAccount(long id, AccountDTO account);
}
