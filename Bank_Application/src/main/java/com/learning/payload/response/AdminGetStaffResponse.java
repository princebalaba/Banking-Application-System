package com.learning.payload.response;

import com.learning.enums.EStatus;

import lombok.Data;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 14.-오후 9:18:38
 */
@Data
public class AdminGetStaffResponse {
	private long staffId ;
	private String staffName;
	private EStatus status;
}
