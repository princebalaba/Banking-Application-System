package com.learning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.AdminDTO;
import com.learning.entity.StaffDTO;
import com.learning.entity.UserDTO;
import com.learning.enums.EStatus;
import com.learning.exceptions.IdNotFoundException;
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
	private StaffRepository staffRepo;
	@Autowired
	private UserServiceImpl userService;

	@Override
	public StaffDTO addStaff(StaffDTO staff) {
		return staffRepo.save(staff);
	}

	@Override
	public List<StaffDTO> getAllStaff() {
		// TODO Auto-generated method stub
		return staffRepo.findAll();
	}

	@Override
	public AdminDTO getAdmin() {
		// TODO Auto-generated method stub
		AdminDTO admin = adminRepo.findByUsername("Admin").orElseThrow(() -> new IdNotFoundException(""));
		return admin;
	}

	public String setEnable(SetEnableRequest request) {

		StaffDTO staff = staffRepo.findById(request.getStaffId())
				.orElseThrow(() -> new NoDataFoundException("Staff Not Found"));
		staff.setStatus(request.getStatus());
		staffRepo.save(staff);
		return "Staff status updated";
	}

	@Override
	public StaffDTO updateStaffStatus(StaffDTO staff) {
		// TODO Auto-generated method stub
		StaffDTO prev = staffRepo.findById(staff.getId())
				.orElseThrow(() -> new NoDataFoundException("Staff Not Found"));
		prev.setStatus(staff.getStatus());
		staffRepo.save(prev);
	return  prev;
	}

}
