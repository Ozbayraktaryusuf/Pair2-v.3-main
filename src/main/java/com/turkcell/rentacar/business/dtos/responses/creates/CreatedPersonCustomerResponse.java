package com.turkcell.rentacar.business.dtos.responses.creates;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatedPersonCustomerResponse {
	
	private int id;
	private String firstName;
	private String lastName;
	private String nationalNumber;
	private LocalDateTime createdDate;
}
