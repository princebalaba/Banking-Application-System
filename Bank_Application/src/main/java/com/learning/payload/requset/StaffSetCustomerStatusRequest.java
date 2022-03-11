package com.learning.payload.requset;

import com.learning.enums.EStatus;

import lombok.Data;

@Data
public class StaffSetCustomerStatusRequest {

	private Long customerId;
	private  EStatus status;
}