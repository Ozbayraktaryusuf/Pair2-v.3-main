package com.turkcell.rentacar.business.dtos.requests.updates;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class UpdateCorporateCustomerRequest {
	@NotNull
	@Size(min = 1)
	private String corporateName;

	@NotNull
	@Size(min =10, max=10)
	private String taxIdNumber;
}
