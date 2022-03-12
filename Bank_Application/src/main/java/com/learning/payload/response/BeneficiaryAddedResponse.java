package com.learning.payload.response;

import com.learning.enums.Active;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiaryAddedResponse {
private Long  beneficiaryAccountNo;  

private String beneficiaryName;

private    Active active; 
}
