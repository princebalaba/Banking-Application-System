package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.learning.entity.UserDTO;



/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 4.-오후 10:39:04
 */
public interface UserRepository extends JpaRepository<UserDTO, Long> {

}
