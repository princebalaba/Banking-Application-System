package com.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.AccountDTO;
import com.learning.entity.BeneficiaryDTO;
import com.learning.entity.StaffDTO;
import com.learning.entity.UserDTO;
import com.learning.enums.Active;
import com.learning.enums.Approved;
import com.learning.enums.ERole;
import com.learning.enums.EStatus;
import com.learning.exceptions.IdNotFoundException;
import com.learning.payload.requset.StaffSetCustomerStatusRequest;
import com.learning.payload.response.StaffGetAccountResponse;
import com.learning.payload.response.StaffGetCustomerByIdResponse;
import com.learning.payload.response.StaffGetCustomerResponse;
import com.learning.repo.AccountRepo;
import com.learning.repo.BeneficiaryRepo;
import com.learning.repo.StaffRepository;
import com.learning.repo.UserRepository;
import com.learning.service.StaffService;
import com.learning.service.UserService;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 8.-오후 5:27:01
 */
@Service
public class StaffServiceImpl implements StaffService{
	@Autowired
	StaffRepository repo ;
	
	@Autowired
	BeneficiaryRepo beneficiaryRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	AccountRepo accountRepo;
	
	@Autowired
	UserService userService;
	//get Bu user Id
	@Override
	public Optional<UserDTO> getUserById(long id) {
		return userRepo.findById(id);
	}
	
	//get all users
	@Override
	public List<StaffGetCustomerResponse> getAllUsers() {
		List<StaffGetCustomerResponse> getUsers = new ArrayList<>();
		
		userRepo.findAll().stream().forEach(
				e->{
					StaffGetCustomerResponse user =new StaffGetCustomerResponse();
					user.setCustomerId(e.getId());
					user.setCustomerName(e.getFullname());
					user.setStatus(e.getStatus());
					
					getUsers.add(user);
				}
				);
		
		
		return getUsers;
		
	}
	//getByUserName
	@Override
	public Optional<UserDTO> getUserByUserName(String name) {
		return userRepo.findByUsername(name);
	}
	//does user exist
	@Override
	public boolean existsById(long id) {
		return repo.existsById(id);
		 
	}

	@Override
	public StaffGetAccountResponse getAccountDetails(Long accountNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override 
	public List<BeneficiaryDTO> getUnapprovedBeneficiaries() {
		// TODO Auto-generated method stub
		
		List<BeneficiaryDTO>  beneficiaries = beneficiaryRepo.findAll();
		
		beneficiaries.removeIf(beneficiary -> beneficiary.getActive().equals(Active.NO));
		
		return beneficiaries;
	}
	@Override 
	public List<BeneficiaryDTO> getApprovedBeneficiaries() {
		// TODO Auto-generated method stub
		
		List<BeneficiaryDTO>  beneficiaries = beneficiaryRepo.findAll();
		
		beneficiaries.removeIf(beneficiary -> beneficiary.getActive().equals(Active.YES));
		
		return beneficiaries;
	}

	@Override
	public StaffSetCustomerStatusRequest setCustomerStatus(StaffSetCustomerStatusRequest request) {
		// TODO Auto-generated method stub
		StaffSetCustomerStatusRequest response = new StaffSetCustomerStatusRequest();
		;
		if(userRepo.existsById(request.getCustomerId())) {
			UserDTO user = userRepo.findById(request.getCustomerId()).orElseThrow();
			
			user.setStatus(request.getStatus());
			
			UserDTO updatedUser = userRepo.save(user);
			
			response.setCustomerId(updatedUser.getId());
			response.setStatus(updatedUser.getStatus());
			
		}else { 
			throw new IdNotFoundException("Staff with id: "+request.getCustomerId()+ " not found");
		}
		return response;
	}

	@Override
	public List<StaffDTO> getAllStaff() {
		// TODO Auto-generated method stub
		List<StaffDTO> staff =repo.findAll();


		return staff;
	}

	@Override
	public StaffDTO addStaff(StaffDTO staff) {
		// TODO Auto-generated method stub
		return repo.save(staff);
	}

	@Override
	public void removeStaff(Long staffId) {
		// TODO Auto-generated method stub
		
		if(repo.existsById(staffId)) {
			repo.deleteById(staffId);
		}else { 
			throw new IdNotFoundException("Staff with id: "+staffId+ " not found");
		}
		
	}

	@Override
	public List<AccountDTO> getUnapprovedAccounts() {
		// TODO Auto-generated method stub
		
		List<AccountDTO> unapprovedAccounts =  accountRepo.findAll();
		
		unapprovedAccounts.removeIf(account -> account.getApproved().equals(Approved.YES));
		return unapprovedAccounts;
	}

	@Override
	public List<UserDTO> getAllCustomers() {
		// TODO Auto-generated method stub
		
		List <UserDTO> response = userRepo.findAll();
		
		response.removeIf(

				user ->  user.getRoles().removeIf(customerRole-> !customerRole.getRoleName().equals(ERole.ROLE_CUSTOMER))
				
				
				)
			
				
				;
		
		
		
		return response;
	}

	@Override
	public UserDTO setCustomerEnabledDisabled(Long customerId) {
		// TODO Auto-generated method stub
		
		List <UserDTO> users = getAllCustomers();
		
	
		
		users.removeIf(u -> u.getId()!=customerId);
		
		if(users.size() !=1) {
			
			throw new IdNotFoundException("Approving of account was not successful");
		}
		
		UserDTO user = users.get(0);
		
		if(user.getStatus().equals(EStatus.DISABLED)) {
			user.setStatus(EStatus.ENABLE);
		}else {
			user.setStatus(EStatus.DISABLED);
		}
		
		return userService.updateUser(user);
		
		
	}

	@Override
	public StaffGetCustomerByIdResponse getCustomerDetailsByID(Long customerId) {
		// TODO Auto-generated method stub
		
		List <UserDTO> users = getAllCustomers();
		
		UserDTO user = users.stream().filter(u -> u.getId()==customerId).findFirst().get();
		
		StaffGetCustomerByIdResponse response = new StaffGetCustomerByIdResponse();
		
		response.setCreated(user.getDateCreated());
		response.setCustomerId(user.getId());
		response.setStatus(user.getStatus());
		response.setCustomerName(user.getFullname());
		
		return response;
	}
	
	

	

	

}
