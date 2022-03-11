package com.learning.payload.response;

import java.time.LocalDate;

import com.learning.enums.Approved;

public class StaffApproveBeneficiaryResponse {

   private Long fromCustomer;
   private Long beneficiaryAcNo;
   private LocalDate beneficiaryAddedDate; 
   private Approved approved;
  
}
