package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.entity.AccountDTO;
import com.learning.entity.BeneficiaryDTO;
import com.learning.entity.StaffDTO;
import com.learning.entity.UserDTO;
import com.learning.payload.requset.StaffSetCustomerStatusRequest;
import com.learning.payload.response.StaffGetAccountResponse;
import com.learning.payload.response.StaffGetCustomerByIdResponse;
import com.learning.payload.response.StaffGetCustomerResponse;




/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 8.-오후 4:31:17
 */
public interface  StaffService {

	//staffInterface
	public Optional<UserDTO> getUserById(long id);
	public List<StaffGetCustomerResponse> getAllUsers();
	public Optional<UserDTO> getUserByUserName(String name);
	public boolean existsById(long id);
	
	
	StaffGetAccountResponse getAccountDetails(Long accountNo);

	List<BeneficiaryDTO> getUnapprovedBeneficiaries();
	
	List<BeneficiaryDTO> getApprovedBeneficiaries();
	
	StaffSetCustomerStatusRequest setCustomerStatus(StaffSetCustomerStatusRequest request);
	
	StaffGetCustomerByIdResponse getCustomerDetailsByID(Long customerId);
	
	public List<StaffDTO> getAllStaff();
	
	StaffDTO addStaff(StaffDTO staff);
	
	void removeStaff(Long staffId);
	List <AccountDTO> getUnapprovedAccounts();
	
	List <UserDTO> getAllCustomers();
	UserDTO setCustomerEnabledDisabled(Long customerId); 
	

//ApproveBeneficiaryResponse approveBeneficiary(ApproveBeneficiaryRequest request);
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
