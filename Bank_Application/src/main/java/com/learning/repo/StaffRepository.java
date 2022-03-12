package com.learning.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.StaffDTO;
import com.learning.entity.UserDTO;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 8.-오후 4:31:37
 */
@Repository
public interface StaffRepository extends JpaRepository<StaffDTO, Long> {
	boolean existsByUsername(String username);
	Optional<UserDTO> findByUsername(String username);
	
}
