package com.turkcell.rentacar.business.dtos.responses.getById;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class GetByIdExtraServicesResponse {
    private int id;
    private String name;
    private double price;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
