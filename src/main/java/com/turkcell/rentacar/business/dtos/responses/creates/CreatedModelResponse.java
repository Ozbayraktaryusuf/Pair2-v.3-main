package com.turkcell.rentacar.business.dtos.responses.creates;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatedModelResponse {
    private int id;
    private String name;
    private LocalDateTime createdDate;
    private String brandName;
    private String fuelName;
    private String transmissionName;
}
