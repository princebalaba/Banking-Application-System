package com.learning.payload.response;

import java.time.LocalDateTime;

import com.learning.enums.Active;
import com.learning.enums.Approved;
import com.learning.enums.EStatus;

import lombok.Data;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 3. 14.-오후 9:34:54
 */
@Data
public class StaffGetBeneficiaryResponse {
	private long fromCustomer;
	private long beneficiaryAcNo;
	private LocalDateTime BeneficiaryAddedDate;
	private Active approved;
}
