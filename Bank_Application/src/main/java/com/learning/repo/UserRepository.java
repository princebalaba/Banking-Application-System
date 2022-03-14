package com.learning.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.learning.entity.UserDTO;



/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 4.-오후 10:39:04
 */
@Repository
public interface UserRepository extends JpaRepository<UserDTO, Long> {
	boolean existsByUsername(String username);
	Optional<UserDTO> findByUsername(String username);
//	UserDTO findByBeneficiaries(Long userId);
	public void deleteUserDTOById(Long employeeId);
}
