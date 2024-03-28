package com.turkcell.rentacar.business.dtos.responses.updates;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UpdateExtraServicesResponse {
    private int id;
    private String name;
    private double price;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
