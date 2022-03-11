package com.learning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.StaffDTO;
import com.learning.repo.AdminRepo;
import com.learning.service.AdminService;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 11.-오후 5:08:19
 */
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminRepo adminRepo;
	
	
	@Override
	public StaffDTO addStaff(StaffDTO staff) {
		return adminRepo.save(staff);
	}

	@Override
	public List<StaffDTO> getAllStaff() {
		// TODO Auto-generated method stub
		return adminRepo.findAll();
	}

	

}
