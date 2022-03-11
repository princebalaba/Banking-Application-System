package com.learning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.BeneficiaryDTO;
import com.learning.enums.Approved;
import com.learning.payload.response.StaffApproveBeneficiaryResponse;
import com.learning.repo.BeneficiaryRepo;
import com.learning.service.BeneficiaryService;

@Service
public class BeneficiaryServiceImpl  implements BeneficiaryService{

	@Autowired
	BeneficiaryRepo repo;
	
//	@Override
	public List<BeneficiaryDTO> getBeneficiaries() {
//		// TODO Auto-generated method stub
//		
//		List<BeneficiaryDTO> notApproved = repo.findAll()
//				.stream()
//				.filter( beneficiary -> beneficiary
//						.getActive()
//						.equals(Approved.NO))
//						.toList()
//				
//				
//				;
		return null;
	}

	@Override
	public BeneficiaryDTO getBeneficiaryByAccountNumber(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElseThrow();
	}

	@Override
	public List<BeneficiaryDTO> getAllBeneficiaries() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public List<BeneficiaryDTO> getAllApprovedBeneficiaries() {
		// TODO Auto-generated method stub
		List<BeneficiaryDTO>  beneficiaries = repo.findAll();
		
		beneficiaries.removeIf(beneficiary->beneficiary
				.getApproved()
				.equals(Approved.YES));
		return beneficiaries;
	}

	@Override
	public List<BeneficiaryDTO> getAllUnapprovedBeneficiaries() {
		// TODO Auto-generated method stub
	List<BeneficiaryDTO>  beneficiaries = repo.findAll();
		
		beneficiaries.removeIf(beneficiary->beneficiary
				.getApproved()
				.equals(Approved.NO));
		return beneficiaries;
	}

	@Override
	public void removeBeneficiaryByAccountNumber(Long id) {
		// TODO Auto-generated method stub
		
	repo.deleteById(id);
	
	
		
	}

	@Override
	public BeneficiaryDTO updateBeneficiary( BeneficiaryDTO newBeneficiary) {
		// TODO Auto-generated method stub
		
		if(repo.existsById(newBeneficiary.getBeneficiaryAccount())) {
			
			return repo.save(newBeneficiary);
		}
		throw new RuntimeException("Sorry Beneficiary " + newBeneficiary.getBeneficiaryAccount() + " not found");
	}

	@Override
	public BeneficiaryDTO createBeneficiary(BeneficiaryDTO beneficiary) {
		// TODO Auto-generated method stub
		return repo.save(beneficiary);
	}

	@Override
	public StaffApproveBeneficiaryResponse approveBeneficiary() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
