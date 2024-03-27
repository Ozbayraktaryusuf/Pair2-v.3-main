package com.turkcell.rentacar.business.dtos.requests.creates;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateModelRequest {
    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @NotNull
    private String brandName;

    @NotNull
    private String fuelName;

    @NotNull
    private String transmissionName;
}
