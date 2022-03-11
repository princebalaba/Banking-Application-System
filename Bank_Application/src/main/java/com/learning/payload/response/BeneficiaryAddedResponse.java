package com.learning.payload.response;

import com.learning.enums.Approved;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiaryAddedResponse {
private Long  beneficiaryAccountNo;  

private String beneficiaryName;

private    Approved approved; 
}
