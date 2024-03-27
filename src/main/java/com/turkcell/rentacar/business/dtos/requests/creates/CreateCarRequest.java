package com.turkcell.rentacar.business.dtos.requests.creates;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCarRequest {
    @NotNull
    private int modelYear;
    @NotNull
    private int minimumFindexScore;
    @NotNull
    private double dailyPrice;
    @NotNull
    private String modelName;
}
