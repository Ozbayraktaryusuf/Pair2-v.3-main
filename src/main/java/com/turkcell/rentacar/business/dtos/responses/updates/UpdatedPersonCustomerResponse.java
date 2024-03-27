package com.turkcell.rentacar.business.dtos.responses.updates;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatedPersonCustomerResponse {
	private int id;
	private String firstName;
	private String lastName;
	private String nationalNumber;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
}
