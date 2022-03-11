package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.entity.BeneficiaryDTO;
import com.learning.entity.UserDTO;
import com.learning.response.StaffGetAccountResponse;




/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 8.-오후 4:31:17
 */
public interface  StaffService {

	//staffInterface
	public Optional<UserDTO> getUserById(long id);
	public List<UserDTO> getAllUsers();
	public Optional<UserDTO> getUserByUserName(String name);
	public boolean existsById(long id);
	
	
	StaffGetAccountResponse getAccountDetails(Long accountNo);

	List<BeneficiaryDTO> getUnapprovedBeneficiaries();
	
	List<BeneficiaryDTO> getApprovedBeneficiaries();

//	ApproveBeneficiaryResponse approveBeneficiary(ApproveBeneficiaryRequest request);
//
//	List<AccountApprovalSummary> getUnapprovedAccounts();
//
//	ApproveAccountResponse approveAccount(ApproveAccountRequest request);
//
//	List<CustomerSummary> getCustomers();
//
//	String setCustomerEnabled(SetEnabledRequest request);
//
//	CustomerSummary getCustomer(Long customerId);
//
//	String staffTransferFunds(TransferRequestStaff request);
}
