package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.AccountDTO;

@Repository
public interface AccountRepo  extends JpaRepository<AccountDTO, Long>{
	


}
