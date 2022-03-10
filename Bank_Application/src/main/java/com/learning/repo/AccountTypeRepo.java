package com.learning.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.AccountTypeDTO;
import com.learning.entity.Role;
import com.learning.enums.AccountType;
import com.learning.enums.ERole;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 10.-오후 2:15:22
 */
@Repository
public interface AccountTypeRepo extends JpaRepository<AccountTypeDTO, Integer>  {
	public Optional<AccountTypeDTO> findByAccountType(AccountType type);
}
