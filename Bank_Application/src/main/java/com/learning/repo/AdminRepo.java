package com.learning.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.AdminDTO;
import com.learning.entity.UserDTO;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 11.-오후 5:05:09
 */
@Repository
public interface AdminRepo extends JpaRepository< AdminDTO, Long> {
	Optional<AdminDTO> findByUsername(String username);
}
