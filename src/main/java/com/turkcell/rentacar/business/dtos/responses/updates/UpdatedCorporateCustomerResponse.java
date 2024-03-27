package com.turkcell.rentacar.business.dtos.responses.updates;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatedCorporateCustomerResponse {
	private int id;
	private String corporateName;
	private String taxIdNumber;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
}
