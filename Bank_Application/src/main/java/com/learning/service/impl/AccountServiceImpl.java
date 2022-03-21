package com.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.AccountDTO;
import com.learning.entity.UserDTO;
import com.learning.enums.Approved;
import com.learning.exceptions.IdNotFoundException;
import com.learning.payload.requset.ApproveAccountRequest;
import com.learning.repo.AccountRepo;
import com.learning.service.AccountService;
import com.learning.service.UserService;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountRepo repo;
	
	@Autowired
	UserService userService;
	@Override
	public AccountDTO getAccountById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElseThrow(
				
				() -> new IdNotFoundException("Account Id: "+id+" not found")
				);
	}

	@Override
	public AccountDTO getAccountByAccountNumber(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElseThrow(() -> new IdNotFoundException("Account Number: "+id+" not found")
				);
	}

	@Override
	public List<AccountDTO> getAllAcounts() {
		// TODO Auto-generated method stub
		List<AccountDTO> response = repo.findAll();
		
	
		
		return response ;
	}

	@Override
	public List<AccountDTO> getAllApprovedAcounts() {
		// TODO Auto-generated method stub
		List<AccountDTO> accounts = repo.findAll();
		accounts.removeIf(m->m.getApproved().equals(Approved.NO));
		return accounts;
	}

	@Override
	public List<AccountDTO> getAllUnapprovedAcounts() {
		// TODO Auto-generated method stub
		List<AccountDTO> accounts = repo.findAll();
		accounts.removeIf(m->m.getApproved().equals(Approved.YES));
		return accounts;
	}
	
	

	@Override
	public Boolean removeAccountById(Long id) {
		// TODO Auto-generated method stub
		repo.findById(id).ifPresent(account->repo.delete(account));
		//repo.deleteById(id);
		return true;
	}

	@Override
	public AccountDTO updateAccount(Long id, AccountDTO newAccount) {
		// TODO Auto-generated method stub
		repo.findById(id).ifPresent(account->{
			
			account.setAccountBalance(newAccount.getAccountBalance());
//			account.setBeneficiaries(newAccount.getBeneficiaries());
			account.setApproved(newAccount.getApproved());
			account.setAccountType(newAccount.getAccountType());
			account.setAccountNumber(newAccount.getAccountNumber());
			
			repo.save(account);
			
		});
		return repo.findById(id).orElseThrow(() -> new IdNotFoundException("Account Id: "+id+" not found")
				);
	}

	@Override
	public AccountDTO createAccount(AccountDTO account) {
		// TODO Auto-generated method stub
		return repo.save(account);
	}

	@Override
	public Boolean accountExists(Long id) {
		// TODO Auto-generated method stub
		return repo.existsById(id);
	}

	@Override
	public AccountDTO getAccount(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElseThrow(
				() -> new IdNotFoundException("account with id: "+ id +" not found")
				);
	}

	@Override
	public AccountDTO updateAccount(long id, AccountDTO account) {
			AccountDTO prev = repo.findById(id).orElseThrow(()-> new IdNotFoundException("account with id: "+ id +" not found")) ; 
		prev.setAccountBalance(account.getAccountBalance());
		prev.setTransactions(account.getTransactions());
			return repo.save(prev);
	}

	@Override
	public AccountDTO updateAccount(AccountDTO newAccount) {
		// TODO Auto-generated method stub
if(repo.existsById(newAccount.getAccountNumber())) {
			
			return repo.save(newAccount);
		}
		throw new RuntimeException("Sorry Beneficiary " + newAccount.getAccountNumber() + " not found");
	}

	@Override
	public Optional<AccountDTO> findAccountById(long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public List<ApproveAccountRequest> getToBeApproved() {
		// TODO Auto-generated method stub
		List<ApproveAccountRequest> responseList =  new ArrayList<>();
		List<AccountDTO> accs= getAllUnapprovedAcounts();
		
		for(AccountDTO a : accs) {
			
			ApproveAccountRequest response = new ApproveAccountRequest();
			
			response.setAccountNumber(a.getAccountNumber());
			response.setAccountType(a.getAccountType());
			response.setCustomerId(a.getCustomerId());
			response.setApproved(a.getApproved());
			response.setDateCreated(a.getDateOfCreation());
			UserDTO user = userService.getUserById(a.getCustomerId()).get();
			response.setCustomerName(user.getFullname());
			
			responseList.add(response);
		}
		return responseList;
	}
	
	
}
