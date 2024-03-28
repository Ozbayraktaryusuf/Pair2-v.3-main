package com.turkcell.rentacar.business.dtos.responses.creates;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateRentalforPersonResponse {
    private int id;
	private int carID;
	private int personCustomerId;
    private int paymentId;
    private LocalDateTime dateSent;
    private LocalDateTime dateReturned;
}
