package com.learning.service;

import java.util.Optional;

import com.learning.entity.AccountTypeDTO;
import com.learning.enums.AccountType;
import com.learning.repo.AccountTypeRepo;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 10.-오후 2:18:29
 */
public interface AccountTypeService {
	public Optional<AccountTypeDTO> getAccountTypeByName(AccountType type);
}
