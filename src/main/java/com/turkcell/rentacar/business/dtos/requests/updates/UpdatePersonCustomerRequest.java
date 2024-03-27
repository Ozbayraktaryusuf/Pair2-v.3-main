package com.turkcell.rentacar.business.dtos.requests.updates;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class UpdatePersonCustomerRequest {
	@NotNull
	@Size(min = 1)
	private String firstName;
	@NotNull
	@Size(min = 1)
	private String lastName;

}
