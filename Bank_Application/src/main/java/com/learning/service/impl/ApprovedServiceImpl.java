package com.learning.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.ApprovedDTO;
import com.learning.enums.Approved;
import com.learning.repo.ApprovedRepo;
import com.learning.service.ApprovedService;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 10.-오후 2:37:49
 */
@Service
public class ApprovedServiceImpl implements ApprovedService{
	@Autowired
	private ApprovedRepo approvedRepo;
	@Override
	public Optional<ApprovedDTO> getRoleName(Approved roleName) {
		// TODO Auto-generated method stub
		return approvedRepo.findByApprovedStatus(roleName);
	}

}
