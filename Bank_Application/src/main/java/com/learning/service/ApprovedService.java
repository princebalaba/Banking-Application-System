package com.learning.service;

import java.util.Optional;

import com.learning.entity.ApprovedDTO;
import com.learning.entity.Role;
import com.learning.enums.Approved;
import com.learning.enums.ERole;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 10.-오후 2:35:41
 */
public interface ApprovedService {
	public Optional<ApprovedDTO> getRoleName(Approved roleName);
}
