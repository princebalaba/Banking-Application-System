package com.learning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.BeneficiaryDTO;
import com.learning.enums.Active;
import com.learning.enums.Approved;
import com.learning.exceptions.IdNotFoundException;
import com.learning.payload.response.StaffApproveBeneficiaryResponse;
import com.learning.repo.BeneficiaryRepo;
import com.learning.service.BeneficiaryService;

@Service
public class BeneficiaryServiceImpl  implements BeneficiaryService{

	@Autowired
	BeneficiaryRepo repo;
	


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
				.getActive()
				.equals(Active.NO));
		return beneficiaries;
	}

	@Override
	public List<BeneficiaryDTO> getAllUnapprovedBeneficiaries() {
		// TODO Auto-generated method stub
	List<BeneficiaryDTO>  beneficiaries = repo.findAll();
		
	
		return beneficiaries;
	}

	@Override
	public void removeBeneficiaryByAccountNumber(long id) {
		// TODO Auto-generated method stub
		
	repo.deleteById(id);
	
	
		
	}

	@Override
	public BeneficiaryDTO updateBeneficiary( BeneficiaryDTO newBeneficiary) {
		// TODO Auto-generated method stub
		
		if(repo.existsById(newBeneficiary.getBeneficiaryId())) {
			
			return repo.save(newBeneficiary);
		}
		throw new RuntimeException("Sorry Beneficiary " + newBeneficiary.getAccountNumber() + " not found");
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

	@Override
	public BeneficiaryDTO getBeneficiaryById(Long beneficiaryId) {
		// TODO Auto-generated method stub
		System.out.println("id enter"+beneficiaryId);
		BeneficiaryDTO b=repo.findById(beneficiaryId).orElseThrow( () ->new IdNotFoundException("Beneficiary id is invalid"));
		
		System.out.println("Ben = Status" +b.getActive() +" benId:"+b.getBeneficiaryId() +" Ben acc num: "+ b.getAccountNumber());
		
		return repo.findById(beneficiaryId).orElseThrow( () ->new IdNotFoundException("Beneficiary id is invalid"));
	}
	
}
