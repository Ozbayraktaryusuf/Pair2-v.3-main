package com.turkcell.rentacar.business.dtos.responses.creates;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatedCorporateCustomerResponse {

	private int id;
	private String corporateName;
	private String taxIdNumber;
	private LocalDateTime createdDate;
}
