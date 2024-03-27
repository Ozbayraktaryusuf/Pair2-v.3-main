package com.turkcell.rentacar.business.dtos.requests.creates;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePersonCustomerRequest {
@NotNull
@Size(min = 1)
private String firstName;
@NotNull
@Size(min = 1)
private String lastName;

@NotNull
@Size(min =11, max=11)
private String  nationalNumber;
}
