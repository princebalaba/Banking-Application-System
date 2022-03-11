package com.learning.payload.requset;

import lombok.Data;

@Data
public class StaffTransferRequest {
	  private Long fromAccNumber;
	  private Long toAccNumber;
	  private Double amount; 
	  private String reason;
	  private String by; // (Staff name) 

}
