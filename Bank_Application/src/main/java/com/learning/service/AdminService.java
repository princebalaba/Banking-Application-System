package com.learning.service;

import java.util.List;

import com.learning.entity.StaffDTO;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 11.-오후 5:07:08
 */
public interface AdminService {
	public StaffDTO addStaff (StaffDTO staff) ;
	public List<StaffDTO> getAllStaff();
}
