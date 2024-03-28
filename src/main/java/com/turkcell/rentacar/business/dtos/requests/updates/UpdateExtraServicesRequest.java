package com.turkcell.rentacar.business.dtos.requests.updates;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateExtraServicesRequest {
    @NotNull
    @Size(min = 2,max = 45)
    private String name;

    @NotNull
    private double price;
}
