package com.turkcell.rentacar.business.dtos.responses.updates;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatedModelResponse {
    private int id;
    private String name;
    private String brandName;
    private String fuelName;
    private String transmissionName;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
