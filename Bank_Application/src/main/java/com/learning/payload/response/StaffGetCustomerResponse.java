package com.learning.payload.response;

import com.learning.enums.EStatus;

import lombok.Data;

@Data
public class StaffGetCustomerResponse {

	private Long customerId;
    private String customerName;
    private  EStatus status;

}
