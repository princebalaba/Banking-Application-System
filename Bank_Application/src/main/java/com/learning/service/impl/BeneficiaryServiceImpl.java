package com.learning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.BeneficiaryDTO;
import com.learning.enums.Approved;
import com.learning.repo.BeneficiaryRepo;
import com.learning.service.BeneficiaryService;

@Service
public class BeneficiaryServiceImpl  implements BeneficiaryService{

	@Autowired
	BeneficiaryRepo repo;
	
	public List<BeneficiaryDTO> getBeneficiaries() {
		// TODO Auto-generated method stub
		
		List<BeneficiaryDTO> notApproved = repo.findAll()
				.stream()
				.filter( beneficiary -> beneficiary
						.getActive().equals(Approved.NO)).toList()
				
				
				;
		return notApproved;
	}
}
