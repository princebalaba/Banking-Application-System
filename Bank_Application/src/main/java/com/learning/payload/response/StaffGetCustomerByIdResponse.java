package com.learning.payload.response;

import java.time.LocalDateTime;

import com.learning.enums.EStatus;

import lombok.Data;

@Data
public class StaffGetCustomerByIdResponse {

	
	private Long customerId; 
    private String customerName; 
    private EStatus status;
    private LocalDateTime created;

}
