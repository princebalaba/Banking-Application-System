package com.learning.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.AccountTypeDTO;
import com.learning.entity.ApprovedDTO;
import com.learning.enums.AccountType;
import com.learning.enums.Approved;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 10.-오후 2:34:16
 */
@Repository
public interface ApprovedRepo extends JpaRepository<ApprovedDTO, Integer>  {
	public Optional<ApprovedDTO> findByApprovedStatus (Approved type);
}
