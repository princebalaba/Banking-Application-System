package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.entity.UserDTO;
import com.learning.payload.response.CustomerGetBeneficiaries;



/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 4.-오후 10:39:16
 */
public interface UserService {
	public UserDTO addUser(UserDTO user); // are 
	public Optional<UserDTO> getUserById(long id);
	
	public UserDTO updateUser(UserDTO user, long id );
	UserDTO getUser(Long id);
	UserDTO updateUser(UserDTO user);
	
	Boolean userExistsById(Long userId);
	Boolean existsByUsername(String username);
	
	List<CustomerGetBeneficiaries> getCustomerBeneficiaries(Long userId);
	public void deleteUserById(Long employeeId);
	
	public UserDTO findByUsername(String username);
}
