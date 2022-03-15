package com.learning.payload.response;

import java.time.LocalDateTime;

import com.learning.enums.AccountType;
import com.learning.enums.Approved;

import lombok.Data;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 14.-오후 11:05:11
 */
@Data
public class StaffAccountApproveResponse {
	private AccountType accType; 
	private String customerName; 
	private long accNo;
	private LocalDateTime dateCreated; 
	private Approved approved ;
	private String StaffUserName;
}
