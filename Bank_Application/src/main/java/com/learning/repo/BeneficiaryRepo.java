package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.BeneficiaryDTO;

@Repository
public interface BeneficiaryRepo extends JpaRepository<BeneficiaryDTO, Long> {

}
