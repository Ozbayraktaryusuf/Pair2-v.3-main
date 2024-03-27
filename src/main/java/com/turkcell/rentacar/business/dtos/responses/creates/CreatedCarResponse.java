package com.turkcell.rentacar.business.dtos.responses.creates;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatedCarResponse {
    private int id;
    private int modelYear;
    private int minimumFindexScore;
    private double dailyPrice;
    private String modelName;
    private String state;
    private LocalDateTime createdDate;
}
