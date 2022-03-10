package com.learning.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.Role;
import com.learning.enums.ERole;
import com.learning.repo.RoleRepo;
import com.learning.service.RoleService;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 9.-오후 10:58:47
 */
@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleRepo repo ;
	@Override
	public Optional<Role> getRoleName(ERole roleName) {
		// TODO Auto-generated method stub
		return repo.findByRoleName(roleName);
	}

}
