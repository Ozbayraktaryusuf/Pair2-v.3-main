package com.turkcell.rentacar.business.dtos.requests.creates;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCorporateCustomerRequest {
	@NotNull
	@Size(min = 1)
	private String corporateName;

	@NotNull
	@Size(min =10, max=10)
	private String taxIdNumber;
}
