package com.learning.payload.requset;

import java.time.LocalDateTime;

import com.learning.enums.AccountType;
import com.learning.enums.Approved;

import lombok.Data;

@Data
public class ApproveAccountRequest {
	
   private AccountType  accountType;
   private String customerName;
   private  Long accountNumber;
   private LocalDateTime dateCreated; 
   private Approved approved;
   private Long customerId;
   //private  String staffUserName;


}
