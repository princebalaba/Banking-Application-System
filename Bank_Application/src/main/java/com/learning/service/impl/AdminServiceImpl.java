package com.learning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.StaffDTO;
import com.learning.exceptions.NoDataFoundException;
import com.learning.payload.requset.SetEnableRequest;
import com.learning.repo.AdminRepo;
import com.learning.repo.StaffRepository;
import com.learning.repo.UserRepository;
import com.learning.service.AdminService;
import com.learning.service.StaffService;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 11.-오후 5:08:19
 */
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private StaffRepository staffRepository;
	
	@Autowired
	StaffService staffService;
	
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public StaffDTO addStaff(StaffDTO staff) {
		return staffService.addStaff(staff);
	}

	@Override
	public List<StaffDTO> getAllStaff() {
		// TODO Auto-generated method stub
		return staffService.getAllStaff();
	}
	
	public String setEnable(SetEnableRequest request) {
		
		StaffDTO staff = staffRepository.findById(request.getStaffId())
				.orElseThrow(() -> new NoDataFoundException("Staff Not Found"));
				staff.setStatus(request.getStatus());
				staffRepository.save(staff);
				return "Staff status updated";
	}
	

}
