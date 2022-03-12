package com.learning.service;

import java.util.List;

import com.learning.entity.StaffDTO;
import com.learning.entity.UserDTO;
import com.learning.payload.requset.SetEnableRequest;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 11.-오후 5:07:08
 */
public interface AdminService {
	public StaffDTO addStaff (StaffDTO staff) ;
	public List<StaffDTO> getAllStaff();
	public UserDTO getAdmin();
	String setEnable(SetEnableRequest request);
}
