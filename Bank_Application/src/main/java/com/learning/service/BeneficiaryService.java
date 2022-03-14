package com.learning.service;

import java.util.List;

import com.learning.entity.BeneficiaryDTO;
import com.learning.payload.response.StaffApproveBeneficiaryResponse;

public interface BeneficiaryService {

	
	
	StaffApproveBeneficiaryResponse approveBeneficiary();

	BeneficiaryDTO getBeneficiaryByAccountNumber(Long id);
	List<BeneficiaryDTO> getAllBeneficiaries();
	List<BeneficiaryDTO> getAllApprovedBeneficiaries();
	List<BeneficiaryDTO> getAllUnapprovedBeneficiaries();
	void removeBeneficiaryByAccountNumber(long id);
	BeneficiaryDTO updateBeneficiary(BeneficiaryDTO newBeneficiary);
	BeneficiaryDTO createBeneficiary(BeneficiaryDTO beneficiary);
	BeneficiaryDTO getBeneficiaryById(Long beneficiaryId);
	
}
