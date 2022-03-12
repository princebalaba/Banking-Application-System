package com.learning.service;

import java.util.Optional;

import com.learning.entity.UserDTO;



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
	
}
