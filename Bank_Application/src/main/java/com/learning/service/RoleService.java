package com.learning.service;

import java.util.Optional;

import com.learning.entity.Role;
import com.learning.enums.ERole;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 9.-오후 10:56:59
 */
public interface RoleService {
	public Optional<Role> getRoleName(ERole roleName);
}
