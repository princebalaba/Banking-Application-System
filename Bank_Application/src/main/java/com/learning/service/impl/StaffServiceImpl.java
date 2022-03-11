package com.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.BeneficiaryDTO;
import com.learning.entity.UserDTO;
import com.learning.enums.Approved;
import com.learning.payload.requset.StaffSetCustomerStatusRequest;
import com.learning.payload.response.StaffGetAccountResponse;
import com.learning.payload.response.StaffGetCustomerResponse;
import com.learning.repo.BeneficiaryRepo;
import com.learning.repo.StaffRepository;
import com.learning.repo.UserRepository;
import com.learning.service.StaffService;

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
	//get Bu user Id
	@Override
	public Optional<UserDTO> getUserById(long id) {
		return repo.findById(id);
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
		return repo.findByUsername(name);
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
		
		beneficiaries.removeIf(beneficiary -> beneficiary.getApproved().equals(Approved.NO));
		
		return beneficiaries;
	}
	@Override 
	public List<BeneficiaryDTO> getApprovedBeneficiaries() {
		// TODO Auto-generated method stub
		
		List<BeneficiaryDTO>  beneficiaries = beneficiaryRepo.findAll();
		
		beneficiaries.removeIf(beneficiary -> beneficiary.getApproved().equals(Approved.YES));
		
		return beneficiaries;
	}

	@Override
	public StaffSetCustomerStatusRequest setCustomerStatus(StaffSetCustomerStatusRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
