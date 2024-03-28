package com.turkcell.rentacar.business.dtos.requests.creates;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateRentalforPersonRequest {
	
//	@NotNull
//	private int carId;
//	@NotNull
//	private int customerId;
	@NotNull
	private int paymentId;
}
