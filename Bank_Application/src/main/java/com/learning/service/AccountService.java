package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.entity.AccountDTO;
import com.learning.payload.requset.ApproveAccountRequest;

public interface AccountService {
	
	AccountDTO getAccount(Long id);
	AccountDTO getAccountById(Long id);
	AccountDTO getAccountByAccountNumber(Long id);
	List<AccountDTO> getAllAcounts();
	List<AccountDTO> getAllApprovedAcounts();
	List<AccountDTO> getAllUnapprovedAcounts();
	Boolean removeAccountById(Long id);
	AccountDTO updateAccount(Long id, AccountDTO newAccount);
	AccountDTO updateAccount(AccountDTO newAccount);
	AccountDTO createAccount(AccountDTO account);
	Boolean accountExists(Long id);
	
	List<ApproveAccountRequest> getToBeApproved();

	public AccountDTO updateAccount(long id, AccountDTO account);
	public Optional<AccountDTO> findAccountById(long id);
}
