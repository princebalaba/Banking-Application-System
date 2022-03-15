package com.learning.payload.response;

import com.learning.enums.EStatus;

import lombok.Data;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 14.-오후 11:30:29
 */
@Data
public class StaffUserEnabledDIsabledResponse {
	private long customerId;
	private EStatus status; 
}
