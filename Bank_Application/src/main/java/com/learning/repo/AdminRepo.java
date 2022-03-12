package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.AdminDTO;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 11.-오후 5:05:09
 */
@Repository
public interface AdminRepo extends JpaRepository< AdminDTO, Long> {

}
