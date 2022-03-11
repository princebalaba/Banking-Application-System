package com.learning.service;

import java.util.List;

import com.learning.entity.BeneficiaryDTO;
import com.learning.response.StaffApproveBeneficiaryResponse;

public interface BeneficiaryService {

	
	List<BeneficiaryDTO> getBeneficiaries();
	StaffApproveBeneficiaryResponse approveBeneficiary();
}
