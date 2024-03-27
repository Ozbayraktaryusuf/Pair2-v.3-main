package com.turkcell.rentacar.business.dtos.requests.updates;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCarRequest {
    @NotNull
    private int modelYear;
    @NotNull
    private int minimumFindexScore;
    @NotNull
    private double dailyPrice;
    @NotNull
    private String modelName;
}
