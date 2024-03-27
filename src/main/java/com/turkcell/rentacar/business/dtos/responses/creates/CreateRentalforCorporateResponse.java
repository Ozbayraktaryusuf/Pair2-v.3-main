package com.turkcell.rentacar.business.dtos.responses.creates;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateRentalforCorporateResponse {
	private int carID;
	private int corporateCustomerId;
    private LocalDateTime dateSent;
    private LocalDateTime dateReturned;
}
