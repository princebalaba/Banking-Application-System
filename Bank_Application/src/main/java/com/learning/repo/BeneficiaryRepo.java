package com.learning.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.BeneficiaryDTO;
import com.learning.entity.UserDTO;

@Repository
public interface BeneficiaryRepo extends JpaRepository<BeneficiaryDTO, Long> {
	Optional<BeneficiaryDTO> findByUserId(Long userId);

	List<BeneficiaryDTO> findAllByUserId(Long userId);
}
